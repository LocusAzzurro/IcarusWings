package org.mineplugin.locusazzurro.icaruswings.magic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.Utils;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID)
public class AbstractEffect extends Effect {

	protected ArrayList<ItemStack> CurativeItems = new ArrayList<>();

	public AbstractEffect(EffectType a, int b) {
		super(a, b);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int level) {

	}

	@Override
	public boolean isDurationEffectTick(int tickRemaining, int level) {
		return false;
	};

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_180793_1_, @Nullable Entity p_180793_2_,	LivingEntity p_180793_3_, int p_180793_4_, double p_180793_5_) {

	}

	// 被添加
	public void onAdded(LivingEntity entity, EffectInstance effect, @Nullable EffectInstance oldEffect) {

	}

	// 人为移除
	public void onRemove(LivingEntity entity, EffectInstance effect) {
		// 不要在这里调用 entity.removeEffect 效果，否则会死循环
	}

	// 自行消失
	public void onExpiry(LivingEntity entity, EffectInstance effect) {
		entity.removeEffect(this);
	}

	// 减少
	public void shrinkEffect(LivingEntity entity, int level) {
		if (!entity.isControlledByLocalInstance()) { // 非本地控制
			EffectInstance instance = entity.getEffect(this);
			if (instance != null && !entity.isControlledByLocalInstance()) {
				int l = instance.getAmplifier() - level; // 获得等级
				entity.removeEffect(this); // 移除旧效果
				if (l > -1) {
					entity.addEffect(new EffectInstance(this, this.getDuration(l), l, false, this.visible()));
				}
				;
			}
			;
		}
	};

	protected boolean setEffect(LivingEntity entity, int level, boolean isAdd, boolean force) {
		if (entity.isControlledByLocalInstance()) { // 非本地控制
			EffectInstance instance = entity.getEffect(this);
			if (force || instance == null || level > instance.getAmplifier()) {
				int l = 0;
				if (instance != null) { // 存在效果
					l = level + (isAdd ? instance.getAmplifier() : 0); // 更新
					entity.removeEffect(this); // 移除旧效果
				}
				;
				if (level < 0) {
					return true;
				} else if (this.canBeAddEffect(entity, level)) {
					if (l > this.getMaxLevel()) { // 超过上限
						this.overflow(entity, l - this.getMaxLevel()); // 通知溢出
						l = this.getMaxLevel(); // 收敛
					}
					;
					return entity.addEffect(new EffectInstance(this, this.getDuration(l), l, false, this.visible()));
				}
				;
			}
			;
		}
		return false;
	};

	// 设置
	public boolean setEffect(LivingEntity entity, int level) {
		return this.setEffect(entity, level, false, true);
	}

	// 叠加
	public boolean addEffect(LivingEntity entity, int level) {
		return this.setEffect(entity, level, true, true);
	}

	// 叠加
	public boolean applyEffect(LivingEntity entity, int level) {
		return this.setEffect(entity, level, false, false);
	}

	// 等级溢出时，传入的参数是溢出部分的值
	public void overflow(LivingEntity entity, int overflowLevel) {

	};

	// 持续时间
	public int getDuration(int level) {
		return 100;
	};

	// 最大等级
	public int getMaxLevel() {
		return 4;
	};

	// 使用addEffect方法时，会访问该方法，要监听更底层的添加，请访问 canBeAffected
	public boolean canBeAddEffect(LivingEntity entity, int level) {
		return true;
	}

	// 可见吗
	public boolean visible() {
		return true;
	}

	// 治疗物品（已覆盖，所以牛奶不再生效）
	@Override
	public List<ItemStack> getCurativeItems() {
		return this.CurativeItems;
	}

	// 放入治疗物品
	public void setCurativeItems(ItemStack stack) {
		if (!this.getCurativeItems().stream().anyMatch(stackIn -> stackIn.sameItem(stack))) {
			this.getCurativeItems().add(stack);
		}
	}

	// - - - - 这里是事件，如无必要请勿修改 - - - - //
	// 添加
	@SubscribeEvent
	public static void eventAdd(PotionEvent.PotionAddedEvent event) {
		EffectInstance effect = event.getPotionEffect();
		EffectInstance old = event.getOldPotionEffect();
		if (effect.getEffect() instanceof AbstractEffect) {
			if (old == null || old.getAmplifier() < effect.getAmplifier()) {
				((AbstractEffect) (effect.getEffect())).onAdded(event.getEntityLiving(), effect, old);
			}
		}
	}

	// 被移除
	@SubscribeEvent
	public static void eventRemove(PotionEvent.PotionRemoveEvent event) {
		EffectInstance effect = event.getPotionEffect();
		if (effect != null && effect.getEffect() instanceof AbstractEffect) {
			((AbstractEffect) (effect.getEffect())).onRemove(event.getEntityLiving(), effect);
		}
	}

	// 自然消失
	@SubscribeEvent
	public static void eventExpiry(PotionEvent.PotionExpiryEvent event) {
		EffectInstance effect = event.getPotionEffect();
		// 只处理CoreEffect的内容
		if (effect != null && effect.getEffect() instanceof AbstractEffect) {
			((AbstractEffect) (effect.getEffect())).onExpiry(event.getEntityLiving(), effect);
		}
	}

}
