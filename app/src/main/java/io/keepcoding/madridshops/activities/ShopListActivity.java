package io.keepcoding.madridshops.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsFromCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsFromCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.SetAllShopsAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.SetAllShopsAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImpl;
import io.keepcoding.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManagerDAOImpl;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.NetworkManager;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;
import io.keepcoding.madridshops.fragments.ShopsFragment;
import io.keepcoding.madridshops.navigator.Navigator;
import io.keepcoding.madridshops.util.map.MapPinnable;
import io.keepcoding.madridshops.util.map.MapUtil;
import io.keepcoding.madridshops.util.map.model.ShopPin;
import io.keepcoding.madridshops.views.OnElementClick;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static io.keepcoding.madridshops.util.map.MapUtil.centerMapInPosition;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;

    ShopsFragment shopsFragment;
    private SupportMapFragment mapFragment;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        initializeMap();

    }

    private void checkCacheData() {
        GetIfAllShopsAreCachedInteractor getIfAllShopsAreCachedInteractor = new GetIfAllShopsAreCachedInteractorImpl(this);
        getIfAllShopsAreCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                // all cached already, no need to download things, just read from DB
                readDataFromCache();

            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtainShopsList();
            }
        });
    }

    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    map = googleMap;

                    checkCacheData();

                    addDataToMap(googleMap);
                }
            }
        });
    }

    public void addDataToMap(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        centerMapInPosition(map, 40.411335, -3.674908);
        map.setBuildingsEnabled(true);
        map.setMapType(MAP_TYPE_NORMAL);
        map.getUiSettings().setRotateGesturesEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMyLocationEnabled(true);

        MarkerOptions retiroMarkerOptions = new MarkerOptions()
                .position(new LatLng(40.411335, -3.674908))
                .title("Hello world").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        MarkerOptions retiroMarkerOptions2 = new MarkerOptions()
                .position(new LatLng(40.42, -3.674908))
                .title("Hello world 2").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_camera));

        Marker marker = map.addMarker(retiroMarkerOptions);
        map.addMarker(retiroMarkerOptions2);

    }


    private void readDataFromCache() {
        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImpl(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);
            }
        });
    }

    private void obtainShopsList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(
            new GetAllShopsInteractorCompletion() {
                @Override
                public void completion(Shops shops) {
                    SaveAllShopsIntoCacheManager saveManager = new SaveAllShopsIntoCacheManagerDAOImpl(getBaseContext());
                    SaveAllShopsIntoCacheInteractor saveInteractor = new SaveAllShopsIntoCacheInteractorImpl(saveManager);
                    saveInteractor.execute(shops, new Runnable() {
                        @Override
                        public void run() {
                            SetAllShopsAreCachedInteractor setAllShopsCachedInteractor = new SetAllShopsAreCachedInteractorImpl(getBaseContext());
                            setAllShopsCachedInteractor.execute(true);
                        }
                    });

                    configShopsFragment(shops);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            },
            new InteractorErrorCompletion() {
                @Override
                public void onError(String errorDescription) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        );
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                // TODO: finish
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });

        putShopPinsOnMap(shops);
    }

    private void putShopPinsOnMap(Shops shops) {
        List<MapPinnable> shopPins = ShopPin.shopPinsFromShops(shops);
        MapUtil.addPins(shopPins, map, this);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Shop)) {
                    return;
                }
                Shop shop = (Shop) marker.getTag();
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, shop, 0);
            }
        });
    }
}
