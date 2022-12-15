package fbdius.bn3nid.beabv3;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "Beabv3", acceptedMinecraftVersions = "1.8.9")
public class Beabv3 {

    private static Beabv3 FS2bsxclsa;
    private final Dhb3u2hhd rdbhjasi = new Dhb3u2hhd();
    private final DS3jdz9yrf fjgagf23;
    private final DSFhiu2s gdsjon2s;

    public Beabv3() {
        FS2bsxclsa = this;
        this.fjgagf23 = new DS3jdz9yrf(this);
        this.gdsjon2s = new DSFhiu2s(this);
    }

    public static Beabv3 g() {
        return FS2bsxclsa;
    }

    @EventHandler
    public void s(FMLPreInitializationEvent s2) {
        ClientCommandHandler.instance.registerCommand(this.fjgagf23);
    }
    
    @EventHandler
    public void ir2(FMLInitializationEvent dfj2) {
        MinecraftForge.EVENT_BUS.register(this.gdsjon2s);
    }

    public Dhb3u2hhd z() {
        return this.rdbhjasi;
    }
    
    public DSFhiu2s a() {
        return this.gdsjon2s;
    }
}
