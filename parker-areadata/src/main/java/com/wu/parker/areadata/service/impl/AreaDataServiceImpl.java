package com.wu.parker.areadata.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wu.parker.areadata.dao.AreaDataRepository;
import com.wu.parker.areadata.po.AreaData;
import com.wu.parker.areadata.service.AreaDataService;
import com.wu.parker.common.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: wusq
 * @date: 2018/11/23
 */
@Service
@Transactional
public class AreaDataServiceImpl implements AreaDataService {

    @Autowired
    private AreaDataRepository areaDataRepository;

    private List<AreaData> areaDataList = new ArrayList<>();

    @Override
    public void save() {

        List<String> lines = FileUtils.read("d:/test/area-data.txt");
        String jsonStr = lines.get(0);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonStr);

        // 省
        List<AreaData> provinceList = new ArrayList<>();

        JsonObject provinceJson = jsonObject.getAsJsonObject("86");
        Set<String> provinceCodeSet = provinceJson.keySet();

        provinceCodeSet.forEach(provinceCode -> {
            AreaData province = new AreaData();
            province.setId(provinceCode);
            province.setName(provinceJson.get(provinceCode).getAsString());
            province.setParent("86");
            provinceList.add(province);
            addList(province);

            // 市
            List<AreaData> cityList = new ArrayList<>();
            JsonObject cityJson = jsonObject.getAsJsonObject(provinceCode);
            Set<String> cityCodeSet = cityJson.keySet();
            for(String cityCode:cityCodeSet){
                AreaData city = new AreaData();
                city.setId(cityCode);
                city.setName(cityJson.get(cityCode).getAsString());
                city.setParent(provinceCode);
                cityList.add(city);
                addList(city);

                // 区县
                List<AreaData> countyList = new ArrayList<>();
                JsonObject countyJson = jsonObject.getAsJsonObject(cityCode);
                if(countyJson == null){
                    continue;
                }
                Set<String> countyCodeSet = countyJson.keySet();
                if(countyCodeSet == null || countyCodeSet.size() == 0){
                    continue;
                }
                for(String countyCode:countyCodeSet){
                    AreaData county = new AreaData();
                    county.setId(countyCode);
                    System.out.println(countyCode);
                    county.setName(countyJson.get(countyCode).getAsString());
                    county.setParent(cityCode);
                    countyList.add(county);
                    addList(county);

                    // 乡镇
                    List<AreaData> townList = new ArrayList<>();
                    JsonObject townJson = jsonObject.getAsJsonObject(countyCode);
                    if(townJson == null){
                        continue;
                    }
                    Set<String> townCodeSet = townJson.keySet();
                    if(townCodeSet == null || townCodeSet.size() == 0){
                        continue;
                    }
                    countyCodeSet.forEach(townCode -> {
                        AreaData town = new AreaData();
                        town.setId(townCode);
                        town.setName(townJson.get(townCode).getAsString());
                        town.setParent(countyCode);
                        countyList.add(town);
                        addList(town);
                    });

                }
            }
        });

        // 清空this.areaDataList

        // 保存中国，最终形成一颗树
        AreaData country = new AreaData();
        country.setId("86");
        country.setName("中国");
        this.areaDataList.add(country);

        areaDataRepository.saveAll(this.areaDataList);
    }

    @Override
    public List<AreaData> list(String parent) {
        return areaDataRepository.findByParent(parent);
    }

    void addList(AreaData areaData){
        this.areaDataList.add(areaData);
        // 每2000条数据保存一次
        if(areaDataList.size() >= 2000){
            areaDataRepository.saveAll(areaDataList);
            areaDataList.clear();
        }
    }
}
