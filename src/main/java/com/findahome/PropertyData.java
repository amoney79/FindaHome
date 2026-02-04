package com.findahome;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyData {
    private static List<Property> allProperties = new ArrayList<>();

    static {
        // Mock Data
        allProperties.add(new Property("Skyline Luxury Penthouse", "Kilimani, Nairobi", "KSh 250,000", 250000,
                "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=400&q=80", true,
                "EXCLUSIVE",
                "3 Beds", "3 Baths", "2,500 sqft", "Apartment"));

        allProperties.add(new Property("Garden Oasis Villa", "Karen, Nairobi", "KSh 180,000", 180000,
                "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?auto=format&fit=crop&w=400&q=80", true,
                "EXCLUSIVE",
                "4 Beds", "4 Baths", "4,000 sqft", "Villa"));

        allProperties.add(new Property("Modern Urban Studio", "Westlands, Nairobi", "KSh 85,000", 85000,
                "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=400&q=80", true,
                "EXCLUSIVE",
                "1 Bed", "1 Bath", "600 sqft", "Studio"));

        allProperties.add(new Property("Cozy Family Home", "Runda, Nairobi", "KSh 120,000", 120000,
                "https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?auto=format&fit=crop&w=400&q=80", false,
                "STORY",
                "3 Beds", "2 Baths", "1,800 sqft", "Apartment"));

        allProperties.add(new Property("Luxury Apartment", "Kileleshwa, Nairobi", "KSh 45,000", 45000,
                "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?auto=format&fit=crop&w=400&q=80", true,
                "FEATURED",
                "2 Beds", "2 Baths", "1,200 sqft", "Apartment"));

        allProperties.add(new Property("Executive Mansionette", "Nairobi CBD", "KSh 320,000", 320000,
                "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?auto=format&fit=crop&w=400&q=80", true,
                "PREMIUM",
                "5 Beds", "5 Baths", "5,500 sqft", "Mansionette"));
    }

    public static List<Property> getAll() {
        return allProperties;
    }

    public static List<Property> filter(String type, String location, double minPrice, double maxPrice) {
        return allProperties.stream()
                .filter(p -> (type == null || type.equals("All Types") || p.getType().equalsIgnoreCase(type)))
                .filter(p -> (location == null || location.isEmpty()
                        || p.getLocation().toLowerCase().contains(location.toLowerCase())))
                .filter(p -> (p.getPriceValue() >= minPrice && p.getPriceValue() <= maxPrice))
                .collect(Collectors.toList());
    }
}
