package at.petrak.hexcasting.common.casting.actions.spells

import at.petrak.hexcasting.api.addldata.ADVariantItem
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.xplat.IXplatAbstractions
import net.minecraft.world.item.ItemStack

object OpCycleVariant : SpellAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingEnvironment): SpellAction.Result {
        val (handStack, hand) = ctx.getHeldItemToOperateOn {
            IXplatAbstractions.INSTANCE.findVariantHolder(it) != null
        } ?: throw MishapBadOffhandItem.of(ItemStack.EMPTY.copy(), null, "variant") // TODO: hack

        val variantHolder = IXplatAbstractions.INSTANCE.findVariantHolder(handStack)
            ?: throw MishapBadOffhandItem.of(handStack, hand, "variant")

        return SpellAction.Result(
            Spell(variantHolder),
            0,
            listOf()
        )
    }

    private data class Spell(val variantHolder: ADVariantItem) : RenderedSpell {
        override fun cast(ctx: CastingEnvironment) {
            variantHolder.variant = (variantHolder.variant + 1) % variantHolder.numVariants()
        }
    }
}