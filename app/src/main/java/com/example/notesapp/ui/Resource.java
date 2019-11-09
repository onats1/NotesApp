package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public Resource(@NonNull Status status, @Nullable T data, @NonNull String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static  <T> Resource<T>  success(@NonNull T data, @NonNull String message){
        return new Resource<>(Status.SUCCEESS, data , message);
    }

    public static <T> Resource<T> error(@Nullable T data, @NonNull String msg){
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data){
        return new Resource<>(Status.LOADING, data, null);
    }

    public enum Status { SUCCEESS, ERROR, LOADING }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource<?> resource = (Resource<?>) o;
        return status == resource.status &&
                Objects.equals(data, resource.data) &&
                Objects.equals(message, resource.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }
}
