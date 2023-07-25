package eu.ansquare.trains;

import org.bukkit.entity.Minecart;

public class Link{
    Minecart origCart;
    Minecart newCart;
    public Link(Minecart origCart){
        this.origCart = origCart;
    }
    public void addSecondCart(Minecart minecart){
        this.newCart = minecart;
    }
    public boolean isComplete(){
        if(origCart != null && newCart != null){
            return true;
        }
        return false;
    }
}
