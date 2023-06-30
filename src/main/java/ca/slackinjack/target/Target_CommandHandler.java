package ca.slackinjack.target;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class Target_CommandHandler extends CommandBase {

    private final Target INSTANCE;
    private final String usageString = "Usage: /target [add/clear/colours/list/remove]";

    public Target_CommandHandler(Target targetIn) {
        this.INSTANCE = targetIn;
    }

    @Override
    public String getCommandName() {
        return "target";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Target_ChatFormatting chatFormatting = INSTANCE.getChatFormatting();
        Target_EventHandler eventHandler = INSTANCE.getEventHandler();

        switch (args.length) {
            case 0:
                chatFormatting.addChatMessage(usageString, 1);
                break;
            case 1:
                String theTarget;
                switch (args[0]) {
                    case "add":
                        chatFormatting.addChatMessage("Usage: /target add [player]", 1);
                        break;
                    case "all":
                        eventHandler.enableAutoUpdater();
                        chatFormatting.addChatMessage("Toggling all players", 0);
                        break;
                    case "clear":
                        if (eventHandler.clearLists()) {
                            chatFormatting.addChatMessage("Removing all players", 0);
                        }
                        break;
                    case "list":
                        String targetListBuilder = "";
                        String queuedListBuilder = "";
                        for (String s : eventHandler.getTargettedNamesAsList()) {
                            targetListBuilder = targetListBuilder + (s + ", ");
                        }

                        for (String s : eventHandler.getQueuedNamesAsList()) {
                            queuedListBuilder = queuedListBuilder + (s + ", ");
                        }
                        chatFormatting.addChatMessage("Targeted players: " + targetListBuilder, 0);
                        chatFormatting.addChatMessage("Queued players: " + queuedListBuilder, 2);
                        break;




                    case "remove":
                        chatFormatting.addChatMessage("Usage: /target remove [player]", 1);
                        break;
                    case "help":
                        chatFormatting.addChatMessage(usageString, 1);
                        break;
                    default:
                        theTarget = args[0];
                        eventHandler.addTarget(theTarget, -1);
                        break;
                }
                break;


            case 2:
                String theColour;
                int colourValue;
                switch (args[0]) {

                    case "add":
                        theTarget = args[1];
                        eventHandler.addTarget(theTarget, -1);
                        break;
                    case "remove":
                        theTarget = args[1];
                        eventHandler.removeTarget(theTarget);
                        break;
                    default:
                        theColour = args[1];
                        colourValue = eventHandler.findColourRGBFromName(theColour.toLowerCase());
                        if (colourValue != -1) {
                            theTarget = args[0];
                            eventHandler.addTarget(theTarget, colourValue);
                            break;
                        } else {
                            chatFormatting.addChatMessage(usageString, 1);
                            break;
                        }
                }
                break;




            case 3:
                switch (args[0]) {
                    case "add":
                        theTarget = args[1];
                        theColour = args[2];
                        colourValue = eventHandler.findColourRGBFromName(theColour.toLowerCase());
                        if (colourValue != -1) {
                            eventHandler.addTarget(theTarget, colourValue);
                        } else {
                            chatFormatting.addChatMessage("Invalid colour.", 1);
                        }
                        break;
                    default:
                        chatFormatting.addChatMessage(usageString, 1);
                        break;
                }
                break;
            default:
                chatFormatting.addChatMessage(usageString, 1);
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
