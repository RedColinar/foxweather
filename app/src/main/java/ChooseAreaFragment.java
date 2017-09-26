import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.harry.foxweather.R;
import com.harry.foxweather.db.City;
import com.harry.foxweather.db.Country;
import com.harry.foxweather.db.Province;
import com.harry.foxweather.util.HttpUtil;
import com.harry.foxweather.util.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Harry on 2017/9/25.
 */

public class ChooseAreaFragment extends Fragment {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTRY = 2;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;

    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    private List<Province> provinces;
    private List<City> cities;
    private List<Country> countries;

    private int currentLevel;

    private Province seletedProvince;
    private City seletedCity;

    private final String PROVINCE_TYPE = "provicne";
    private final String CITY_TYPE = "city";
    private final String COUNTRY_TYPE = "country";

    private final String url = "http://guolin.tech/api/china/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    seletedProvince = provinces.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    seletedCity = cities.get(position);
                    queryCountries();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTRY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvicnes();
                }
            }
        });
        queryProvicnes();
    }

    private void queryProvicnes() {
        titleText.setText(getString(R.string.China));
        backButton.setVisibility(View.GONE);
        provinces = DataSupport.findAll(Province.class);
        if (provinces.size() > 0) {
            dataList.clear();
            for (Province province : provinces) {
                dataList.add(province.getProvinceName());
            }
            // 强制刷新 adapter 中的 item
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            String address = url;
            queryFromServer(address, PROVINCE_TYPE);
        }
    }

    private void queryCities() {
        titleText.setText(seletedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cities = DataSupport
                .where("provinceId = ? ", String.valueOf(seletedProvince.getId()))
                .find(City.class);
        if (cities.size() > 0) {
            dataList.clear();
            for (City city : cities) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = seletedProvince.getProvinceCode();
            String address = url + provinceCode;
            queryFromServer(address, CITY_TYPE);
        }
    }

    private void queryCountries() {
        titleText.setText(seletedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countries = DataSupport
                .where("cityId = ? ", String.valueOf(seletedCity.getId()))
                .find(Country.class);
        if (countries.size() > 0) {
            dataList.clear();
            for (Country country : countries) {
                dataList.add(country.getCountryName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTRY;
        } else {
            int provinceCode = seletedProvince.getProvinceCode();
            int cityCode = seletedCity.getCityCode();
            String address = url + provinceCode + "/" + cityCode;
            queryFromServer(address, COUNTRY_TYPE);
        }
    }

    private void queryFromServer(String address, final String type) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;

                if (PROVINCE_TYPE.equals(type)) {
                    result = Utility.handleProvinceResponse(responseText);
                } else if (CITY_TYPE.equals(type)) {
                    result = Utility.handleCityResponse(responseText, seletedProvince.getId());
                } else if (COUNTRY_TYPE.equals(type)) {
                    result = Utility.handleCountryResponse(responseText, seletedCity.getId());
                }

                if (result) {
                    getActivity().runOnUiThread(() -> {
                        closeProgressDialog();

                        if (PROVINCE_TYPE.equals(type)) {
                            queryProvicnes();
                        } else if (CITY_TYPE.equals(type)) {
                            queryCities();
                        } else if (COUNTRY_TYPE.equals(type)) {
                            queryCountries();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> {
                    closeProgressDialog();
                    Toast.makeText(getContext(), getString(R.string.load_failed), Toast.LENGTH_SHORT).show();
                });
            }
        });
        closeProgressDialog();
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
