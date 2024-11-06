package DAO;

import data.Product;
import utilities.MyUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ProductDAO extends HashMap<String, Product> implements IProductDAO {
       
    @Override
    public void add() {
        String id = MyUtils.getString("Input ID (Pxxx): ", "Invalid, please input again. ", "^P\\d{3}$");
        if (this.containsKey(id)) {
            System.err.println("The id already exists");
            System.err.println("Added failed.");
            return;
        }
        String name = MyUtils.getString("Name of product: ", "Invalid, please input again. ");
        String brandId;
        while(true) {
            brandId = MyUtils.getString("Input brand ID (Bxxx): ", "Invalid, please input again. ", "^B\\d{3}$");
            if(Product.getBrandById(brandId) != null)
                break;
            else
                System.err.println("The brand ID does not exist. Please input again.");
        }
        String categoryId;
        while(true) {
            categoryId = MyUtils.getString("Input category ID (Cxxx): ", "Invalid, please input again. ", "^C\\d{3}$");
            if(Product.getCategoryById(categoryId) != null)
                break;
            else
                System.err.println("The category ID does not exist. Please input again.");
        }
        int modelYear = MyUtils.getInteger("Input model year: ", "Invalid, please input again. ", 0, 2024);
        double listPrice = MyUtils.getDouble("Input list price: ", "Invalid, please input again. ", 0);
        this.put(id, new Product(id, name, brandId, categoryId, modelYear, listPrice));
        System.out.println("Here is the product information: ");
        System.out.println(this.get(id).toString());
        System.out.println("Added successfully.");
    }
    
    @Override
    public void search() {
        ArrayList<Product> productList = new ArrayList<>();
        this.forEach((k, v) -> productList.add(v));
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
               return Integer.compare(p1.getModelYear(), p2.getModelYear());
            }
        });
        String search = MyUtils.getString("What do you want to search? ", "Invalid, please input again. ", ".*");
        if (productList.isEmpty()) {
            System.out.println("There is no product.");
            return;
        }
        System.out.println("Search result: ");
        for (Product x : productList) {
            if (x.getName().toLowerCase().contains(search.toLowerCase()))
                System.out.println(x.toString());
        }
    }

    @Override
    public void update() {
        String id;
        do {
            id = MyUtils.getString("Input ID (Pxxx): ", "Invalid, please input again. ", ".*");
            if(!this.containsKey(id) && !id.isEmpty())
                System.err.println("Product doesn't exist.");
            if(id.isEmpty())
                System.err.println("Update failed.");
        } while(!this.containsKey(id));
        System.out.println("This is the product before update: ");
        System.out.println(this.get(id).toString());
        
        String name = MyUtils.getString1("Update name (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
        if(!name.isEmpty())
            this.get(id).setName(name);
        
        String brandId;
        while (true) {
            brandId = MyUtils.getString1("Update brand ID (Bxxx) (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
            if(Product.getBrandById(brandId) != null || brandId.isEmpty())
                break;
            else
                System.err.println("The brand ID does not exist. Please input again.");
        }
        if(!brandId.isEmpty())
            this.get(id).setBrandId(brandId);
        
        String categoryId;
        while (true) {
            categoryId = MyUtils.getString1("Update category ID (Cxxx) (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
            if(Product.getCategoryById(categoryId) != null || categoryId.isEmpty())
                break;
            else
                System.err.println("The category ID does not exist. Please input again.");
        }
        if(!categoryId.isEmpty())
            this.get(id).setCategoryId(categoryId);

        String modelYear = MyUtils.getString1("Update model year (Press Enter if you don't want to update): ", "Invalid, please input again. ", "\\d{4}$");
        if(!modelYear.isEmpty())
            this.get(id).setModelYear(Integer.parseInt(modelYear));
        
        String listPrice = MyUtils.getString1("Update list price (Press Enter if you don't want to update): ", "Invalid, please input again. ", "^\\d+(\\.\\d+)?$");
        if(!listPrice.isEmpty())
            this.get(id).setListPrice(Double.parseDouble(listPrice));
       
        System.out.println("This is the product after update: ");
        System.out.println(this.get(id).toString());
        System.out.println("Updated successfully.");
    }

    @Override
    public void delete() {
        String id;
        do {
            id = MyUtils.getString1("ID of product to delete: ", "Invalid, please input again. ", ".*");
            if(!this.containsKey(id) && !id.isEmpty())
                System.err.println("Product doesn't exist.");
            if(id.isEmpty())
                System.err.println("Delete failed.");
        } while(!this.containsKey(id));
        System.out.println("This is the product before delete: ");
        System.out.println(this.get(id).toString());
        String confirm = MyUtils.getString("Are you sure you want to delete this product? (Y/N) ", "Invalid, please input again. ", "[Yy|Nn]");
        if (confirm.equalsIgnoreCase("Y")) {
            this.remove(id);
            System.out.println("Deleted successfully.");
        }
    }

    @Override
    public void print() {
        ArrayList<Product> productList = new ArrayList<>();
        productList = sort(this.values());
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p1.getListPrice() == p2.getListPrice())
                    return p1.getName().compareTo(p2.getName());
                return Double.compare(p2.getListPrice(), p1.getListPrice());
            }
        });
        if(productList.isEmpty()) {
            System.out.println("There is no product.");
            return;
        }
        for (Product x : productList) {
            System.out.println(x.toString1());
        }
    }
    
    public ArrayList<Product> sort(Collection<Product> liProduct) {
        ArrayList<Product> productList = new ArrayList<>(liProduct);
        Collections.sort(productList);
        return productList;
    }
    
    public boolean loadFromFile(String url) {
        File f = new File(url);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {                
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken();
                String name = st.nextToken().trim();
                String brandId = st.nextToken().trim();
                String categoryId = st.nextToken().trim();
                int modelYear = Integer.parseInt(st.nextToken().trim());
                double listPrice = Double.parseDouble(st.nextToken().trim());
                Product p = new Product(id, name, brandId, categoryId, modelYear, listPrice);
                this.put(id, p);                
                line = br.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error, loading from file " + e);
            return false;
        }
    }
    
    public boolean saveToFile(String url) {
        File f = new File(url);
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            for (Product x : sort(this.values())) 
                osw.write(x.toString() + "\n");
            osw.flush();
            osw.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error, saving to file " + e);
            return false;
        }
    }
    
    //Thêm chức năng cập nhật có giá = x cho tất cả các product có năm = y (x,y nhập từ bàn phím), 
    //cho biết có bnhiu products đc cập nhật
    public void find(double x, int y) {
        ArrayList<Product> listProduct = new ArrayList<>();
        this.forEach((k, v) -> listProduct.add(v));
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : listProduct) {
            if (p.getModelYear() == y) {
                p.setListPrice(x);
                result.add(p);
            }
        }
        for (Product p : result) {
            System.out.println(p.toString1());
        }
        System.out.println("Updated " + result.size() + " product(s) in total.");
    }
}
