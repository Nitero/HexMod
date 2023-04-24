package at.petrak.hexcasting.api.casting.mishaps

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.FrozenColorizer
import net.minecraft.world.item.DyeColor

class MishapEvalTooMuch : Mishap() {
    override fun accentColor(ctx: CastingEnvironment, errorCtx: Context): FrozenColorizer =
        dyeColor(DyeColor.BLUE)

    override fun execute(ctx: CastingEnvironment, errorCtx: Context, stack: MutableList<Iota>) {
        ctx.mishapEnvironment.drown()
    }

    override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context) =
        error("eval_too_deep")
}