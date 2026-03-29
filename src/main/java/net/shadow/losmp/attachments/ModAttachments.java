package net.shadow.losmp.attachments;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.Identifier;
import net.shadow.losmp.Losmp;

public class ModAttachments {


    public static final AttachmentType<Integer> TIME = AttachmentRegistry.createDefaulted(
                    new Identifier(Losmp.MOD_ID, "time"), () -> 0);
}