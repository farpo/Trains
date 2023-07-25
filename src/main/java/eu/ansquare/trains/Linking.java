package eu.ansquare.trains;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Linking {
    Trains instance;
    Map<Player, Link> activePlayerLinks;
    TrainFinder tl;
    public Linking(){
        this.instance = Trains.instance;
        activePlayerLinks = new HashMap<>();
        tl = new TrainFinder();
    }
    public void addLink(Player player, Minecart cart){
        if(activePlayerLinks.containsKey(player)){
            Link link = activePlayerLinks.get(player);
            activePlayerLinks.remove(player);
            if(tl.isInTrain(cart)){
                player.sendMessage("Debug");

                return;
            }
            link.addSecondCart(cart);
            String linkResult;
            linkResult = "Link unsuccesful";
            if(link.isComplete()){
                if(tl.isInTrain(link.origCart)){
                    Train train = tl.getTrain(link.origCart);
                    linkResult = train.add(link);
                }
                else {
                    Train train = new Train(link.origCart, player.getWorld(), link.newCart);
                    instance.trainsList.add(train);
                    linkResult = "Train created";
                }
            }
            player.sendActionBar(Component.text(linkResult));
        }
        else{
            Link link = new Link(cart);
            activePlayerLinks.put(player, link);
        }
    }
}

