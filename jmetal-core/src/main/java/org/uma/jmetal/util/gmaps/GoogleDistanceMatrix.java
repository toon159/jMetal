package org.uma.jmetal.util.gmaps;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

public class GoogleDistanceMatrix {
    private static final String API_KEY = "AIzaSyBTAVA1lXA1SGu0rmqEkVBi6q_1bNGjDlY";
    private static GeoApiContext context;

    static {
        context = new GeoApiContext().setApiKey(API_KEY);
    }


    public GoogleDistanceMatrix getEstimateRouteTime(DateTime time, Boolean isForCalculateArrivalTime, DirectionsApi.RouteRestriction routeRestriction, LatLng departure, LatLng... arrivals) {
        try {
            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
            if (isForCalculateArrivalTime) {
                req.departureTime(time);
            } else {
                req.arrivalTime(time);
            }
            if (routeRestriction == null) {
                routeRestriction = DirectionsApi.RouteRestriction.TOLLS;
            }
            DistanceMatrix trix = req.origins(departure)
                    .destinations(arrivals)
                    .mode(TravelMode.DRIVING)
                    .avoid(routeRestriction)
                    .language("fr-FR")
                    .await();
            return trix;

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {

    }

}
