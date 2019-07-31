package com.example.swastha_naari;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

class Migration implements RealmMigration {
    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            final RealmObjectSchema userSchema = schema.get("FormModel");
            userSchema
                    .addField("FirstPregnancyAge", String.class)
                    .addField("ChildNumber", String.class)
                    .addField("MenopauseAge", String.class)
                    .addField("BirthSpacing", String.class)
                    .addField("MenstrualInterval", String.class);
            oldVersion++;
        }
        if (oldVersion == 1) {
            schema.create("User")
                    .addField("ID", String.class, FieldAttribute.PRIMARY_KEY)
                    .addField("username", String.class)
                    .addField("password", String.class);
            oldVersion++;
        }
    }
}