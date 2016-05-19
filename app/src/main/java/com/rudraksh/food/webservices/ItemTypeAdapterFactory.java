package com.rudraksh.food.webservices;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapterFactory;

import java.io.IOException;

/**
 * Created by dell8 on 19/5/16.
 */
public class ItemTypeAdapterFactory implements TypeAdapterFactory {
 @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this,type);
        final TypeAdapter<JsonElement> elementAdapter =  gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(com.google.gson.stream.JsonWriter out, T value) throws IOException {
                delegate.write(out,value);
            }

            @Override
            public T read(com.google.gson.stream.JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);

                return delegate.fromJsonTree(jsonElement);
            }


        }.nullSafe();
    }
}
