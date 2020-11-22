package ir.allahverdi.session14webservice.entity;

public class Product {
    private int id;
    private String tittle;
    private int price;
    private String imageId;

    public Product(int id, String tittle, int price, String imageId) {
        this.id = id;
        this.tittle = tittle;
        this.price = price;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", price=" + price +
                ", imageId='" + imageId + '\'' +
                "}\n";
    }
}
