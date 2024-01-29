package pojo;

import java.util.List;

public class PostRequestBody {

    private String name;
    private String job;
    private List<String> languages;
    private List<CityRequest> cityList;
    private int id;
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }



    public int getId() {
        return id;
    }



    public List<CityRequest> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityRequest> cityList) {
        this.cityList = cityList;
    }



    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
