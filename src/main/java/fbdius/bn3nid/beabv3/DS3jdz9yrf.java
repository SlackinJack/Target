package fbdius.bn3nid.beabv3;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class DS3jdz9yrf extends CommandBase {

    private final Beabv3 fip2;
    private final String cjkh3 = "Usage: /target [add/clear/colours/list/remove]";

    public DS3jdz9yrf(Beabv3 rthgj) {
        this.fip2 = rthgj;
    }

    @Override
    public String getCommandName() {
        return "target";
    }

    @Override
    public String getCommandUsage(ICommandSender saju) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender dhjs2, String[] j9ds) throws CommandException {
        Dhb3u2hhd giouy32 = fip2.z();
        DSFhiu2s giu2t8 = fip2.a();

        switch (j9ds.length) {
            case 0:
                giouy32.fjksav(cjkh3, 1);
                break;
            case 1:
                String tg2iouy;
                switch (j9ds[0]) {
                    case "add":
                        giouy32.fjksav("Usage: /target add [player]", 1);
                        break;
                    case "all":
                        giu2t8.ik3hr();
                        giouy32.fjksav("Toggling all players", 0);
                        break;
                    case "clear":
                        if (giu2t8.lgh2zm()) {
                            giouy32.fjksav("Removing all players", 0);
                        }
                        break;
                    case "list":
                        String hl2g895r = "";
                        String goijfsg789s = "";
                        for (String l2ut1 : giu2t8.jhv2bs()) {
                            hl2g895r = hl2g895r + (l2ut1 + ", ");
                        }

                        for (String efgiuw2 : giu2t8.xv12gus8()) {
                            goijfsg789s = goijfsg789s + (efgiuw2 + ", ");
                        }
                        giouy32.fjksav("Targeted players: " + hl2g895r, 0);
                        giouy32.fjksav("Queued players: " + goijfsg789s, 2);
                        break;


                    case "remove":
                        giouy32.fjksav("Usage: /target remove [player]", 1);
                        break;
                    case "help":
                        giouy32.fjksav(cjkh3, 1);
                        break;
                    default:
                        tg2iouy = j9ds[0];
                        giu2t8.uiopysao27(tg2iouy, -1);
                        break;
                }
                break;

            case 2:
                String tghpu23i;
                int t3fouy1v;
                switch (j9ds[0]) {

                    case "add":
                        tg2iouy = j9ds[1];
                        giu2t8.uiopysao27(tg2iouy, -1);
                        break;
                    case "remove":
                        tg2iouy = j9ds[1];
                        giu2t8.xzgwq2(tg2iouy);
                        break;
                    default:
                        tghpu23i = j9ds[1];
                        t3fouy1v = giu2t8.fbxzhjkl82(tghpu23i.toLowerCase());
                        if (t3fouy1v != -1) {
                            tg2iouy = j9ds[0];
                            giu2t8.uiopysao27(tg2iouy, t3fouy1v);
                            break;
                        } else {
                            giouy32.fjksav(cjkh3, 1);
                            break;
                        }
                }
                break;


            case 3:
                switch (j9ds[0]) {
                    case "add":
                        tg2iouy = j9ds[1];
                        tghpu23i = j9ds[2];
                        t3fouy1v = giu2t8.fbxzhjkl82(tghpu23i.toLowerCase());
                        if (t3fouy1v != -1) {
                            giu2t8.uiopysao27(tg2iouy, t3fouy1v);
                        } else {
                            giouy32.fjksav("Invalid colour.", 1);
                        }
                        break;
                    default:
                        giouy32.fjksav(cjkh3, 1);
                        break;
                }
                break;
            default:
                giouy32.fjksav(cjkh3, 1);
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender gh2pu34) {
        return true;
    }
}
