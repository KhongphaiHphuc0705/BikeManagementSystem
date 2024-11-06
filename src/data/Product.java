package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Product implements Comparable<Product> {
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private double listPrice;
    private static HashMap<String, Brand> brandMap = new HashMap<>();
    private static HashMap<String, Category> categoryMap = new HashMap<>();

    //Constructors
    public Product(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    //Getters and setters methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + brandId + ", " + categoryId + ", " + modelYear + ", " + listPrice;
    }
    
    public String toString1() {
        return id + ", " + name + ", " + getBrandById(brandId) + ", " + getCategoryById(categoryId) + ", " + modelYear + ", " + listPrice;
    }
    
    @Override
    public int compareTo(Product that) {
        return this.id.compareToIgnoreCase(that.id);
    }
    
    public static String getBrandById(String bid) {
        File f = new File(".\\Brand.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {                
                StringTokenizer st = new StringTokenizer(line, ",");
                String brandId = st.nextToken();
                String name = st.nextToken().trim();
                String country = st.nextToken().trim();
                Brand b = new Brand(brandId, name, country);
                brandMap.put(brandId, b);                
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Brand b = brandMap.get(bid);
        return (b != null) ? b.getName() : null;
    }
    
    public static String getCategoryById(String cid) {
        File f = new File(".\\Category.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {                
                StringTokenizer st = new StringTokenizer(line, ",");
                String categoryId = st.nextToken();
                String name = st.nextToken().trim();
                Category c = new Category(categoryId, name);
                categoryMap.put(categoryId, c);                
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Category c = categoryMap.get(cid);
        return (c != null) ? c.getName() : null;
    }
}
