# 伊卡洛斯之翼 TeaCon 最佳实践指南

**模组ID：** `locusazzurro_icaruswings`  
**作者：** LocusAzzurro  
**版本：** 26.1.2-0.7.0  
**最后更新：** 2026-06-23

---

## 1. 关于本文档
本文档列出了来自“伊卡洛斯之翼”模组的一些物品及物品组合，这些内容可能会在TeaCon展示中无意间破坏展馆、绕过保护机制、导致服务器卡顿或提供不公平的优势。  
鉴于展示期间所有模组的物品均可自由使用，请在建造或测试前仔细阅读本说明。

本文档中的关键词“必须”(MUST)、“禁止”(MUST NOT)、“强制要求”(REQUIRED)、“应当”(SHALL)、“不应当”(SHALL NOT)、“推荐”(SHOULD)、“不推荐”(SHOULD NOT)、“建议”(RECOMMENDED)、“不建议”(NOT RECOMMENDED)、“可以”(MAY)和“可选”(OPTIONAL)，应参照对应英文关键词，按照 RFC 2119 中的说明进行解释。

---

## 2. 严重程度分级
| 等级 | 含义 |
|------|------|
| **🔴 严重** | 可能导致服务器崩溃、世界损坏、绕过保护机制且无有效反制手段。未经明确允许，请勿使用。 |
| **🟠 高危** | 提供完全无敌、飞行、瞬间大范围破坏或大量物品复制。很可能破坏展馆的平衡性。 |
| **🟡 中等** | 提供较强优势（如临时飞行、透视、大范围挖掘），但可通过领地等机制加以限制。 |
| **🟢 低** | 轻微辅助功能，但可能让参展者感到意外（如穿透非固体方块移动、部分夜视效果等）。通常安全。 |

---

## 3. 物品详细说明
*所有物品及物品组合按严重程度排序。请逐项阅读，尤其注意🔴严重与🟠高危条目。*

- **物品/组合名称：** 德墨忒尔+德墨忒尔能量粒子  
  **游戏内 ID：** `locusazzurro_icaruswings:demeter`,`locusazzurro_icaruswings:demeter_charge`   
  **类型：** 武器  
  **严重程度：** 🔴 严重  
  **简介：**  远程+近战武器，远程攻击需要能量粒子作为弹药。  
  **可能导致的问题：** 发射的弹射物在接触固体方块后会生成约8x8x8范围的地形生成覆盖。用于近战持续攻击玩家会造成即死效果。   
  **使用说明：**
  - 在服务器端配置中，**必须**将`allow_demeter_change_terrain`项目设为`false`。 
  - **禁止**以任何方式向一般玩家提供此物品以及弹药。


- **物品/组合名称：** 空域羽翼  
  **游戏内 ID：** `locusazzurro_icaruswings:ikaros_wings`,`locusazzurro_icaruswings:nymph_wings`,`locusazzurro_icaruswings:astraea_wings`,`locusazzurro_icaruswings:chaos_wings`,`locusazzurro_icaruswings:hiyori_wings`,`locusazzurro_icaruswings:melan_wings`  
  **类型：** 装备  
  **严重程度：** 🟠 高危  
  **简介：**  动力羽翼，相当于无限烟花助推原版鞘翅，且速度更快并提供大量装备加成。  
  **可能导致的问题：** 提供高速飞行，可使用烟花进一步助推，无视物理屏障。   
  **使用说明：**
  - **禁止**以供货箱等方式向一般玩家无限提供。
  - **禁止**在除非本模组展馆或合作展馆中提供。
  - **建议**使用 AreaControl 或“阻扰”效果进行限制。  

  
- **物品/组合名称：** 希腊火桶  
  **游戏内 ID：** `locusazzurro_icaruswings:greek_fire_bucket`     
  **类型：** 流体  
  **严重程度：** 🟠 高危  
  **简介：**  流体，同时具有熔岩与水的特性。  
  **可能导致的问题：** 存在一定问题，放置后难以清理。   
  **使用说明：**
  - **不建议**在除非本模组展馆中放置，若需要放置需注意避免泄漏。


- **物品/组合名称：** 普通羽翼/魔法羽翼  
  **游戏内 ID：** `locusazzurro_icaruswings:feather_wings`,`locusazzurro_icaruswings:colored_feather_wings`,`locusazzurro_icaruswings:golden_feather_wings`,`locusazzurro_icaruswings:paper_wings`,`locusazzurro_icaruswings:magic_wings`,`locusazzurro_icaruswings:flandre_magic_wings`  
  **类型：** 装备  
  **严重程度：** 🟡 中等  
  **简介：**  无动力羽翼，与原版鞘翅机制类似。  
  **可能导致的问题：** 提供滑翔，可使用烟花助推，无视物理屏障。   
  **使用说明：**
  - **不建议**以供货箱等方式向一般玩家无限提供，**可以**提供制作所需的材料。
  - **不建议**在除非本模组展馆或合作展馆中提供。
  - **建议**使用 AreaControl 或“阻扰”效果进行限制。


- **物品/组合名称：** 时间裂缝发生器+时间裂缝粒子  
  **游戏内 ID：** `locusazzurro_icaruswings:time_rift_generator`,`locusazzurro_icaruswings:time_rift_charge`   
  **类型：** 武器  
  **严重程度：** 🟡 中等  
  **简介：**  德墨忒尔下位武器，远程武器，需要粒子作为弹药。  
  **可能导致的问题：** 使用弹射物持续攻击玩家会造成即死效果。   
  **使用说明：**
  - **不建议**向一般玩家提供此物品以及弹药，若提供需进行数量或区域限制。


- **物品/组合名称：** 生长之金果圣酒  
  **游戏内 ID：** `locusazzurro_icaruswings:golden_apple_growth_infused_mead`   
  **类型：** 物品  
  **严重程度：** 🟡 中等  
  **简介：**  消耗品，可将3x3范围草地转化为极乐草地，绵羊转化为金羊。  
  **可能导致的问题：** 不可逆地形修改。   
  **使用说明：**
  - **不建议**向一般玩家提供此物品，若提供需进行数量或区域限制。


- **物品/组合名称：** 阿涅摩伊之杖  
  **游戏内 ID：** `locusazzurro_icaruswings:wind_wand`   
  **类型：** 工具  
  **严重程度：** 🟡 中等  
  **简介：**  消耗空气罐使用，可向前或向后喷射进行位移。  
  **可能导致的问题：** 较强位移，可使玩家越过大部分物理屏障。   
  **使用说明：**
  - **建议**使用 AreaControl 进行限制。


- **物品/组合名称：** 愿望之卡-时空涡流  
  **游戏内 ID：** `locusazzurro_icaruswings:transport_card_chrono_explosion`   
  **类型：** 工具  
  **严重程度：** 🟡 中等  
  **简介：**  消耗品，使用后会发射大量弹射物并使使用者自爆。  
  **可能导致的问题：** 生成大量实体，范围性高额伤害效果。   
  **使用说明：**
  - **不建议**向一般玩家提供此物品，若提供需进行数量或区域限制。


- **物品/组合名称：** 愿望之卡-传送  
  **游戏内 ID：** `locusazzurro_icaruswings:transport_card_chrono_teleport`   
  **类型：** 工具  
  **严重程度：** 🟡 中等  
  **简介：**  消耗品，可写入坐标并消耗传送至写入的坐标。  
  **可能导致的问题：** 可能的物理屏障穿透。   
  **使用说明：**
  - **不建议**向一般玩家提供未写入坐标的卡牌，这可能导致玩家返回不应访问的区域，或绕过物理屏障。

  
- **物品/组合名称：** 蜂蜡系列方块  
  **游戏内 ID：** `locusazzurro_icaruswings:refined_beeswax_block`及衍生     
  **类型：** 方块  
  **严重程度：** 🟢 低  
  **简介：**  拥有较为低的摩擦系数。   
  **可能导致的问题：** 可使玩家获得一定加速。   
  **使用说明：**
  - **可以**在跑酷中使用此类方块。


- **物品/组合名称：** 空气罐  
  **游戏内 ID：** `locusazzurro_icaruswings:*_air_jar`     
  **类型：** 物品  
  **严重程度：** 🟢 低  
  **简介：**  可带来短期效果或提供一定位移。   
  **可能导致的问题：** 可使玩家获得短暂的位移，有可能越过一些低矮的障碍。  
  **使用说明：**
  - **可以**在跑酷中提供此类物品。

  
*感谢你帮助保持 TeaCon 展示的稳定！如有任何关于物品行为的疑问，欢迎随时联系。*