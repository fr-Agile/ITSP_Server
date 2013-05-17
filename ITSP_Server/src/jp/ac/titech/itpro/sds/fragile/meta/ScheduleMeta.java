package jp.ac.titech.itpro.sds.fragile.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-05-17 15:32:26")
/** */
public final class ScheduleMeta extends org.slim3.datastore.ModelMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.util.Date> finishTime = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.util.Date>(this, "fT", "finishTime", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.lang.Boolean> repeat = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.lang.Boolean>(this, "rp", "repeat", java.lang.Boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.util.Date> startTime = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.util.Date>(this, "sT", "startTime", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.Schedule, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ScheduleMeta slim3_singleton = new ScheduleMeta();

    /**
     * @return the singleton
     */
    public static ScheduleMeta get() {
       return slim3_singleton;
    }

    /** */
    public ScheduleMeta() {
        super("Schedule", jp.ac.titech.itpro.sds.fragile.model.Schedule.class);
    }

    @Override
    public jp.ac.titech.itpro.sds.fragile.model.Schedule entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule model = new jp.ac.titech.itpro.sds.fragile.model.Schedule();
        model.setFinishTime((java.util.Date) entity.getProperty("fT"));
        model.setKey(entity.getKey());
        model.setRepeat((java.lang.Boolean) entity.getProperty("rp"));
        model.setStartTime((java.util.Date) entity.getProperty("sT"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("fT", m.getFinishTime());
        entity.setProperty("rp", m.getRepeat());
        entity.setProperty("sT", m.getStartTime());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = (jp.ac.titech.itpro.sds.fragile.model.Schedule) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getFinishTime() != null){
            writer.setNextPropertyName("finishTime");
            encoder0.encode(writer, m.getFinishTime());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getRepeat() != null){
            writer.setNextPropertyName("repeat");
            encoder0.encode(writer, m.getRepeat());
        }
        if(m.getStartTime() != null){
            writer.setNextPropertyName("startTime");
            encoder0.encode(writer, m.getStartTime());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.ac.titech.itpro.sds.fragile.model.Schedule jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.ac.titech.itpro.sds.fragile.model.Schedule m = new jp.ac.titech.itpro.sds.fragile.model.Schedule();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("finishTime");
        m.setFinishTime(decoder0.decode(reader, m.getFinishTime()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("repeat");
        m.setRepeat(decoder0.decode(reader, m.getRepeat()));
        reader = rootReader.newObjectReader("startTime");
        m.setStartTime(decoder0.decode(reader, m.getStartTime()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}