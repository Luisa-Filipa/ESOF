package com.esof.projeto.services.filters;

import java.util.Set;

public interface FilterI<T> {
    Set<T> filter(Set<T> entities);
}