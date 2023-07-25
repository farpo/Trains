package eu.ansquare.trains;

import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.util.Vector;

import javax.swing.text.html.parser.Entity;

public class CartListener implements Listener {
    TrainFinder tf;
    public CartListener(){
        tf = new TrainFinder();
    }
    @EventHandler
    public void onEntityDeath(VehicleDestroyEvent e){
        if(e.getVehicle() instanceof Minecart){
            Minecart cart = (Minecart) e.getVehicle();
            if(tf.isInTrain(cart)){
                tf.getTrain(cart).remove(cart);
            }
        }

    }
    @EventHandler
    public void onVehicleMove(VehicleUpdateEvent e){
        if(e.getVehicle() instanceof Minecart){
            Minecart cart = (Minecart) e.getVehicle();
            if(tf.isInTrain(cart)){
                Train train = tf.getTrain(cart);
                train.movementSpeed = train.mainCart.getVelocity();
                if(!train.isMain(cart)){
                    cart.setVelocity(train.movementSpeed);
                }
            }
        }
    }

}
