package doph.pipocraft.gui;

import doph.pipocraft.system.Ref;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonRegister extends GuiButton {

    public GuiButtonRegister(int offX, int offY) {
        super(Ref.BUTTON_REGISTER, offX + 88 - 24, offY + 48, 48, 20, "Register");
    }



}
