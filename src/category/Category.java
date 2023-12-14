package category;

public class Category {
    private int categoryId;
    private String categoryName  ;
    private String categoryType;

    public Category(int categoryId, String categoryName, String categoryType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public Category(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }
}
