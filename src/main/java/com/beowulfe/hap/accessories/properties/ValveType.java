package com.beowulfe.hap.accessories.properties;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.beowulfe.hap.accessories.Valve;

/**
 * The mode used by a {@link Valve}
 *
 * @author Tim Harper
 */
public enum ValveType {

    GENERIC(0),
    IRRIGATION(1),
    SHOWER(2),
    WATER_FAUCET(3);

    private final static Map<Integer, ValveType> reverse;
    static {
        reverse = Arrays.stream(ValveType.values()).collect(Collectors.toMap(t -> t.getCode(), t -> t));
    }

    public static ValveType fromCode(Integer code) {
        return reverse.get(code);
    }

    private final int code;

    private ValveType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
