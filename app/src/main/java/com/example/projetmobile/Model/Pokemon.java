package com.example.projetmobile.Model;

import java.util.List;

public class Pokemon
{
    private int Id;

    private String num;

    private String name;

    private String img;

    private List<String> type;

    private String height;

    private String weight;

    private String Candy;

    private int CandyCount;

    private String Egg;

    private Double SpawnChance;

    private Double AvgSpawns;

    private String SpawnTime;

    private List<Double> Multipliers;

    private List<String> weaknesses;

    private List<Evolution> next_evolution;

    private List<Evolution> prev_evolution;

    public Pokemon() {
    }

    public Pokemon(int id, String num, String name, String img, List<String> type, String height, String weight, String candy, int candyCount, String egg, Double spawnChance, Double avgSpawns, String spawnTime, List<Double> multipliers, List<String> weaknesses, List<Evolution> evolution, List<Evolution> prevEvolution) {
        Id = id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.type = type;
        this.height = height;
        this.weight = weight;
        Candy = candy;
        CandyCount = candyCount;
        Egg = egg;
        SpawnChance = spawnChance;
        AvgSpawns = avgSpawns;
        SpawnTime = spawnTime;
        Multipliers = multipliers;
        this.weaknesses = weaknesses;
        this.next_evolution = evolution;
        this.prev_evolution= prevEvolution;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return Candy;
    }

    public void setCandy(String candy) {
        Candy = candy;
    }

    public int getCandyCount() {
        return CandyCount;
    }

    public void setCandyCount(int candyCount) {
        CandyCount = candyCount;
    }

    public String getEgg() {
        return Egg;
    }

    public void setEgg(String egg) {
        Egg = egg;
    }

    public Double getSpawnChance() {
        return SpawnChance;
    }

    public void setSpawnChance(Double spawnChance) {
        SpawnChance = spawnChance;
    }

    public Double getAvgSpawns() {
        return AvgSpawns;
    }

    public void setAvgSpawns(Double avgSpawns) {
        AvgSpawns = avgSpawns;
    }

    public String getSpawnTime() {
        return SpawnTime;
    }

    public void setSpawnTime(String spawnTime) {
        SpawnTime = spawnTime;
    }

    public List<Double> getMultipliers() {
        return Multipliers;
    }

    public void setMultipliers(List<Double> multipliers) {
        Multipliers = multipliers;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<Evolution> getNextEvolution() {
        return next_evolution;
    }

    public void setNextEvolution(List<Evolution> evolution) {
        next_evolution = evolution;
    }

    public List<Evolution> getPrevEvolution() {
        return prev_evolution;
    }

    public void setPrevEvolution(List<Evolution> prevEvolution) {
        prev_evolution = prevEvolution;
    }
}
