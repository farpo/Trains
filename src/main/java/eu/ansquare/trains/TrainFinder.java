package eu.ansquare.trains;

import org.bukkit.entity.Minecart;

public class TrainFinder {
    Trains instance;
    public TrainFinder(){
        instance = Trains.instance;
    }
    public boolean isInTrain(Minecart minecart){
        for(Train train : instance.trainsList){
            if(train.carts.contains(minecart)){
                return true;
            }
        }
        return false;
    }
    public Train getTrain(Minecart cart){
        for(Train train : instance.trainsList){
            if(train.carts.contains(cart)){
                return train;
            }
        }
        return null;
    }
}
