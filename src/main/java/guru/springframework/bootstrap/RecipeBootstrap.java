package guru.springframework.bootstrap;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final RecipeRepository recipeRepository;

  public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    recipeRepository.saveAll(getRecipies());
  }

  private List<Recipe> getRecipies() {
    List<Recipe> recipies = new ArrayList<>(2);

    Optional<UnitOfMeasure> eachUmoOptional = unitOfMeasureRepository.findByDescription("Each");
    UnitOfMeasure eachUmo = eachUmoOptional.orElseThrow(() -> new RuntimeException("Each UMO not found"));

    Optional<UnitOfMeasure> tablespoonUmoOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
    UnitOfMeasure tablespponUmo = tablespoonUmoOptional.orElseThrow(() -> new RuntimeException("Tablespoon UMO not found"));

    Optional<UnitOfMeasure> teaspoonUmoOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
    UnitOfMeasure teaspoonUmo = teaspoonUmoOptional.orElseThrow(() -> new RuntimeException("Teaspoon UMO not found"));

    Optional<UnitOfMeasure> dashUmoOptional = unitOfMeasureRepository.findByDescription("Dash");
    UnitOfMeasure dashUmo = dashUmoOptional.orElseThrow(() -> new RuntimeException("Dash UMO not found"));

    Optional<UnitOfMeasure> pintUmoOptional = unitOfMeasureRepository.findByDescription("Pint");
    UnitOfMeasure pintUmo = pintUmoOptional.orElseThrow(() -> new RuntimeException("Pint UMO not found"));

    Optional<UnitOfMeasure> cupUmoOptional = unitOfMeasureRepository.findByDescription("Cup");
    UnitOfMeasure cupUmo = cupUmoOptional.orElseThrow(() -> new RuntimeException("Cup UMO not found"));

    return recipies;
  }
}
