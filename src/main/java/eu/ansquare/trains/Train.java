package eu.ansquare.trains;

import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Train {
    public UUID id;
    public boolean state;
    public LinkedList<Minecart> carts;
    public Minecart mainCart;
    public World world;
    public Vector movementSpeed;

    public Train(Minecart mainCart, World currentWorld, Minecart secondcart){
        this.mainCart = mainCart;
        this.world = currentWorld;
        carts = new LinkedList<>();
        carts.add(mainCart);
        carts.add(secondcart);
        state = false;
    }

    public String add(Link link){
        Minecart origCart = link.origCart;
        Minecart newCart = link.newCart;
        if(!newCart.getWorld().equals(world)){
            return "Link failed, not the same world";
        }
        else if(origCart.getLocation().distance(newCart.getLocation()) > 2){
            return "Link failed, carts too far apart";
        }
        else {
            if(carts.getFirst().equals(origCart)){
                carts.addFirst(newCart);
                return "Link succesful";
            }
            else if(carts.getLast().equals(origCart)) {
                carts.addLast(newCart);
                return "Link succesful";
            }
            else {
                return "Link failed, attempted to link to center of train";
            }
        }
    }


    public void remove(Minecart minecart){
        if(carts.contains(minecart)){
            if(!minecart.equals(mainCart)){
                carts.remove(minecart);
            }
        }
    }
    public boolean isMain(Minecart cart){
        if(cart.equals(mainCart)){
            return true;
        }
        else{
            return false;
        }
    }
    public void setMain(Minecart cart){
        mainCart = cart;
    }
}
