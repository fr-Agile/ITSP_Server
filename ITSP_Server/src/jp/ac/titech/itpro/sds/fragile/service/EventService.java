package jp.ac.titech.itpro.sds.fragile.service;

import jp.ac.titech.itpro.sds.fragile.meta.EventMeta;
import jp.ac.titech.itpro.sds.fragile.model.Event;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class EventService {
    private static EventMeta meta = EventMeta.get();
    
    public static Event createEvent (User user) {
        Event Event = new Event();
        Key key = Datastore.allocateId(Event.class);
        Event.setKey(key);
        Event.getOwener().setModel(user);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(Event);
        tx.commit();
        return Event;
    }
}
