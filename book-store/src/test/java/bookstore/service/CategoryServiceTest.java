package bookstore.service;

import bookstore.dto.request.SaveCategoryRequest;
import bookstore.dto.request.UpdateCategoryRequest;
import bookstore.dto.response.CategoryResponseDto;
import bookstore.exception.GeneralException;
import bookstore.model.Category;
import bookstore.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class CategoryServiceTest {

    private CategoryService categoryService;

    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void ShouldReturnCategoryResponseDto_WhenGivenSaveCategoryRequest() {
        //given
        SaveCategoryRequest saveCategoryRequest = new SaveCategoryRequest("name");
        Category category = Category.builder()
                .name(saveCategoryRequest.name())
                .build();
        CategoryResponseDto categoryResponseDto = CategoryResponseDto.convertToCategoryResponse(category);
        //when
        Mockito.when(categoryRepository.save(any())).thenReturn(category);
        //then
        CategoryResponseDto result = categoryService.saveCategory(saveCategoryRequest);
        assertEquals(categoryResponseDto, result);
        assertEquals(categoryResponseDto.name(), result.name());
        assertEquals(categoryResponseDto.id(), result.id());

        Mockito.verify(categoryRepository).save(any());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any());

    }

    @Test
    void shouldReturnCategoryResponseDto_WhenGivenUpdateCategoryDtoAndCategoryExists() {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(
                1L,
                "updatedName");
        Category category = Category.builder()
                .name(updateCategoryRequest.name())
                .build();
        CategoryResponseDto categoryResponseDto = CategoryResponseDto.convertToCategoryResponse(category);


        Mockito.when(categoryRepository.findById(any())).thenReturn(java.util.Optional.of(category));

        Mockito.when(categoryRepository.save(any())).thenReturn(category);
        CategoryResponseDto result = categoryService.updateCategory(updateCategoryRequest);
        assertEquals(categoryResponseDto, result);
        assertEquals(categoryResponseDto.name(), result.name());
        assertEquals(categoryResponseDto.id(), result.id());

        Mockito.verify(categoryRepository).findById(any());
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(any());

        Mockito.verify(categoryRepository).save(any());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any());
    }

    @Test
    void shouldThrowGeneralException_WhenGivenUpdateCategoryDtoAndCategoryDoesNotExist() {

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(
                1L,
                "updatedName");

        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.updateCategory(updateCategoryRequest))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Category with id: " + updateCategoryRequest.id() + " does not exist");

        Mockito.verify(categoryRepository).findById(any());
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(any());
    }

    @Test
    void shouldReturnCategoryResponseDtoList(){
        Category category = Category.builder()
                .name("name")
                .build();
        Category category2 = Category.builder()
                .name("name2")
                .build();
        List<CategoryResponseDto> categoryResponseDtoList = List.of(
                CategoryResponseDto.convertToCategoryResponse(category), CategoryResponseDto.convertToCategoryResponse(category2));

        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category, category2));

        List<CategoryResponseDto> result = categoryService.getAllCategories();
        //assertEquals(categoryResponseDtoList, result);
        assertEquals(categoryResponseDtoList.get(0).name(), result.get(0).name());
        assertEquals(categoryResponseDtoList.get(1).id(), result.get(1).id());

        Mockito.verify(categoryRepository).findAll();
        Mockito.verify(categoryRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw GeneralException(\"Category not found\") when given id and category does not exist")
    void shouldThrowGeneralException_WhenGivenIdAndCategoryDoesNotExists() {

        //given

        Long categoryId = 1L;

        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> categoryService.findById(categoryId))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Category not found");

        verify(categoryRepository).findById(anyLong());
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw GeneralException(\"Category not found\") when given name and category does not exist")
    void shouldReturnCategory_WhenGivenNameAndCategoryExists() {

        String categoryName = "name";

        Category category = Category.builder()
                .name("name")
                .build();

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        Category result = categoryService.findByName(categoryName);
        assertEquals(category, result);

        verify(categoryRepository).findByName(anyString());
        verify(categoryRepository, times(1)).findByName(anyString());
    }
    @Test
    @DisplayName("Should throw GeneralException(\"Category not found\") when given name and category does not exist")
    void shouldThrowGeneralException_WhenGivenNameAndCategoryDoesNotExists() {

        //given

        String categoryName = "name";

        //when
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> categoryService.findByName(categoryName))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Category not found");

        verify(categoryRepository).findByName(anyString());
        verify(categoryRepository, times(1)).findByName(anyString());
    }



    @AfterEach
    void tearDown() {
    }
}