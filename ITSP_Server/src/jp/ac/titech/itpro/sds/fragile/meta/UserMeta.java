package jp.ac.titech.itpro.sds.fragile.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-05-17 15:10:35")
/** */
public final class UserMeta extends org.slim3.datastore.ModelMeta<jp.ac.titech.itpro.sds.fragile.model.User> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User> email = new org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User>(this, "eM", "email");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User> firstName = new org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User>(this, "fN", "firstName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User> lastName = new org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User>(this, "lN", "lastName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User> password = new org.slim3.datastore.StringAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User>(this, "pW", "password");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.ac.titech.itpro.sds.fragile.model.User, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final UserMeta slim3_singleton = new UserMeta();

    /**
     * @return the singleton
     */
    public static UserMeta get() {
       return slim3_singleton;
    }

    /** */
    public UserMeta() {
        super("User", jp.ac.titech.itpro.sds.fragile.model.User.class);
    }

    @Override
    public jp.ac.titech.itpro.sds.fragile.model.User entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.ac.titech.itpro.sds.fragile.model.User model = new jp.ac.titech.itpro.sds.fragile.model.User();
        model.setEmail((java.lang.String) entity.getProperty("eM"));
        model.setFirstName((java.lang.String) entity.getProperty("fN"));
        model.setKey(entity.getKey());
        model.setLastName((java.lang.String) entity.getProperty("lN"));
        model.setPassword((java.lang.String) entity.getProperty("pW"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("eM", m.getEmail());
        entity.setProperty("fN", m.getFirstName());
        entity.setProperty("lN", m.getLastName());
        entity.setProperty("pW", m.getPassword());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
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
        jp.ac.titech.itpro.sds.fragile.model.User m = (jp.ac.titech.itpro.sds.fragile.model.User) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getFirstName() != null){
            writer.setNextPropertyName("firstName");
            encoder0.encode(writer, m.getFirstName());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLastName() != null){
            writer.setNextPropertyName("lastName");
            encoder0.encode(writer, m.getLastName());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder0.encode(writer, m.getPassword());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.ac.titech.itpro.sds.fragile.model.User jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.ac.titech.itpro.sds.fragile.model.User m = new jp.ac.titech.itpro.sds.fragile.model.User();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("firstName");
        m.setFirstName(decoder0.decode(reader, m.getFirstName()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("lastName");
        m.setLastName(decoder0.decode(reader, m.getLastName()));
        reader = rootReader.newObjectReader("password");
        m.setPassword(decoder0.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}