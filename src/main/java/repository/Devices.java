package repository;

import model.Device;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Devices {
    private static Devices instance;
    private List<Device> devices;

    private Devices() {
        devices = new ArrayList<>();
        for(long i=0;i<30;i++)
        {
            Device temp = new Device();
            temp.setId(i);
            temp.setName("Device "+i);
            temp.setPrice(i*i);
            devices.add(temp);
        }

    }

    public static synchronized Devices getInstance() {
        if (instance == null)
            instance = new Devices();
        return instance;
    }

    public Device getDeviceById(long id)
    {
        for(Device n : devices)
        {
            if(n.getId()==id)
                return n;
        }
        return null;
    }

    public Device getDeviceByName(String name)
    {
        for(Device n : devices)
        {
            if(n.getName().equals(name))
                return n;
        }
        return null;
    }
    public void show()
    {
        System.out.println("Devices list:");
        for(Device n : devices)
        {
            System.out.println(String.format("%5d %s",n.getId(),n.getName()));
        }
    }
}
