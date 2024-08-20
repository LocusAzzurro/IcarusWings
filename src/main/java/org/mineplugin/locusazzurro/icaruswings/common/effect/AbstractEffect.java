package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ModData.MOD_ID)
public abstract class AbstractEffect extends MobEffect {

	protected ArrayList<ItemStack> CurativeItems = new ArrayList<>() ;

	public AbstractEffect(MobEffectCategory a, int b ) {
		super( a, b ) ;
	}

	@Override
	public void applyEffectTick( LivingEntity entity, int level ) {

	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_180793_1_, @Nullable Entity p_180793_2_, LivingEntity p_180793_3_, int p_180793_4_, double p_180793_5_ ) {

	}

	// 被初次添加
	public void onFirstAdded(LivingEntity entity, MobEffectInstance effect, @Nullable MobEffectInstance oldEffect ) {
		this.onAdded( entity, effect, oldEffect ) ;
	}

	// 被添加
	public void onAdded(LivingEntity entity, MobEffectInstance effect, @Nullable MobEffectInstance oldEffect ) {

	}
	// 人为移除
	public void onRemove(LivingEntity entity, MobEffectInstance effect ) {
		// 不要在这里调用 entity.removeEffect 效果，否则会死循环
	}
	// 自行消失
	public void onExpiry( LivingEntity entity, MobEffectInstance effect ) {
		// entity.removeEffect( this ) ;
	}

	/** 设置一个效果
	 *
	 * @param entity   实体
	 * @param expectationLevel   期望的等级
	 * @param isAdd   是添加吗
	 * @param force   强制应用吗
	 * @return   添加的成功与否
	 */
	protected boolean setEffect(LivingEntity entity, int expectationLevel, boolean isAdd, boolean force ) {
		MobEffectInstance instance = entity.getEffect(this); // 获得实例
		int baseLevel, newLevel;
		boolean isUpdate = false ;
		if ( instance != null ) { // 存在效果
			baseLevel = instance.getAmplifier( ) ; // 初始
			if ( force || ( isAdd && expectationLevel < 0 ) ) { // 强制或减少
				entity.removeEffect( this ) ; // 移除旧效果
				isUpdate = true ;
			}
		} else { // 不存在效果
			baseLevel = -1 ; // 初始
		}
		;
		newLevel = isAdd ? baseLevel + expectationLevel : expectationLevel ;
		if ( newLevel >= 0 ) {
			if ( newLevel > this.getMaxLevel( ) ) { // 超过上限
				this.overflow( entity, newLevel - this.getMaxLevel( ) ); // 通知溢出
				newLevel = this.getMaxLevel( ); // 限定
				isUpdate = true ; // 溢出默认视为更新
			}
			if ( entity.addEffect( new MobEffectInstance(this, this.getDuration(newLevel), newLevel, false, this.visible( ) ) ) ) {
				isUpdate = true ;
			} ;
		} ;
		return isUpdate ;
	} ;

	// 设置（强制设置一个效果，无论生物是否又更强的效果）
	public boolean setEffect( LivingEntity entity, int level ) {
		return this.setEffect( entity, level, false, true ) ;
	}
	// 叠加期望的等级
	public boolean addEffect( LivingEntity entity, int level ) {
		return this.setEffect( entity, level, true, false ) ;
	}
	// 减少期望的等级
	public boolean shrinkEffect (LivingEntity entity, int level ) {
		return this.setEffect( entity, -level, true, false ) ;
	} ;
	// 应用（应用一个效果，如果已有更强的效果，则不会生效）
	public boolean applyEffect(LivingEntity entity, int level ) {
		return this.setEffect( entity, level, false, false ) ;
	}
	// 等级溢出时，传入的参数是溢出部分的值
	public void overflow (LivingEntity entity, int overflowLevel ) {
	} ;

	// 持续时间
	public int getDuration ( int level ) {
		return 100 ;
	} ;
	// 最大等级
	public int getMaxLevel ( ) {
		return 4 ;
	} ;

	// 使用addEffect方法时，会访问该方法，要监听更底层的添加，请访问 canBeAffected
	public boolean canBeAddEffect( LivingEntity entity, int level ) {
		return true ;
	}
	// 可见吗
	public boolean visible( ) {
		return true ;
	}

	// 治疗物品
	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {

	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int tickRemaining, int level ) {
		return false ;
	} ;

	// - - - - 这里是事件，如无必要请勿修改 - - - - //
	// 添加
	@SubscribeEvent( priority = EventPriority.LOWEST )
	public static void eventAdd(MobEffectEvent.Added event ) {
		MobEffectInstance effect = event.getEffectInstance( ) ; // 新的效果
		MobEffectInstance old = event.getOldEffectInstance( ) ; // 旧的效果
		if ( effect.getEffect( ) instanceof AbstractEffect) { // 新效果是支持的效果
			AbstractEffect ce = (AbstractEffect) effect.getEffect( ) ;
			boolean flag0 = false ; // 匹配检查
			boolean flag1 = false ; // 等级检查
			if ( old != null  ) {
				flag0 = old.getEffect( ).equals( effect.getEffect( ) ) ; // 匹配
				flag1 = old.getAmplifier( ) < effect.getAmplifier( ) ; // 等级
			} ;
			if ( !flag0 ) { // 初次添加
				ce.onFirstAdded( event.getEntity( ), effect, old ) ; // 通知初次添加
			} else if ( flag1 ) { // 是增强
				ce.onAdded( event.getEntity( ), effect, old ) ; // 通知添加
			} ;
		}
	}
	// 被移除
	@SubscribeEvent( priority = EventPriority.LOWEST )
	public static void eventRemove( MobEffectEvent.Remove event ) {
		MobEffectInstance effect = event.getEffectInstance( ) ;
		if ( effect != null && effect.getEffect( ) instanceof AbstractEffect)  {
			( (AbstractEffect) ( effect.getEffect( ) ) ).onRemove( event.getEntity( ), effect );
		}
	}
	// 自然消失
	@SubscribeEvent( priority = EventPriority.LOWEST )
	public static void eventExpiry( MobEffectEvent.Expired event ) {
		MobEffectInstance effect = event.getEffectInstance( ) ;
		// 只处理CoreEffect的内容
		if ( effect != null && effect.getEffect( ) instanceof AbstractEffect)  {
			// 先移除
			event.getEntity( ).removeEffect( effect.getEffect( ) ) ;
			( (AbstractEffect) ( effect.getEffect( ) ) ).onExpiry( event.getEntity( ), effect ) ;
		}

	}


}