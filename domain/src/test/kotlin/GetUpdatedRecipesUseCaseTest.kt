import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.usecase.GetRecipesFavoriteUseCase
import com.exercise.domain.usecase.GetRecipesNewUseCase
import com.exercise.domain.usecase.GetUpdatedRecipesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUpdatedRecipesUseCaseTest {
    private val getRecipesNewUseCase: GetRecipesNewUseCase = mockk()
    private val getRecipesFavoriteUseCase: GetRecipesFavoriteUseCase = mockk()

    private val getUpdatedRecipesUseCase = GetUpdatedRecipesUseCase(
        getRecipesNewUseCase,
        getRecipesFavoriteUseCase
    )

    @Test
    fun `should return updated recipes with favorite flag`() = runBlocking {
        val recipesFromApi = listOf(
            RecipeEntity(id = 1, name = "Recipe 1", isFavorite = false, image = "", description = ""),
            RecipeEntity(id = 2, name = "Recipe 2", isFavorite = false, image = "", description = "")
        )
        val recipesFromDb = listOf(
            RecipeEntity(id = 1, name = "Recipe 1", isFavorite = true, image = "", description = "")
        )

        coEvery { getRecipesNewUseCase.execute() } returns recipesFromApi
        coEvery { getRecipesFavoriteUseCase.execute() } returns recipesFromDb

        val result = getUpdatedRecipesUseCase.execute()

        val expected = listOf(
            RecipeEntity(id = 1, name = "Recipe 1", isFavorite = true, image = "", description = ""), // Favorito actualizado
            RecipeEntity(id = 2, name = "Recipe 2", isFavorite = false, image = "", description = "")
        )

        assertEquals(expected, result)
        coVerify(exactly = 1) { getRecipesNewUseCase.execute() }
        coVerify(exactly = 1) { getRecipesFavoriteUseCase.execute() }
    }

    @Test
    fun `should return empty list when no recipes from API`() = runBlocking {
        coEvery { getRecipesNewUseCase.execute() } returns emptyList()
        coEvery { getRecipesFavoriteUseCase.execute() } returns listOf(
            RecipeEntity(id = 1, name = "Recipe 1", isFavorite = true, image = "", description = "")
        )

        val result = getUpdatedRecipesUseCase.execute()

        assertEquals(emptyList<RecipeEntity>(), result)
        coVerify(exactly = 1) { getRecipesNewUseCase.execute() }
        coVerify(exactly = 1) { getRecipesFavoriteUseCase.execute() }
    }

    @Test
    fun `should return all recipes with isFavorite false when no favorites in DB`() = runBlocking {
        val recipesFromApi = listOf(
            RecipeEntity(id = 1, name = "Recipe 1", isFavorite = false, image = "", description = ""),
            RecipeEntity(id = 2, name = "Recipe 2", isFavorite = false, image = "", description = "")
        )
        coEvery { getRecipesNewUseCase.execute() } returns recipesFromApi
        coEvery { getRecipesFavoriteUseCase.execute() } returns emptyList()

        val result = getUpdatedRecipesUseCase.execute()

        val expected = recipesFromApi.map { it.copy(isFavorite = false) }
        assertEquals(expected, result)
        coVerify(exactly = 1) { getRecipesNewUseCase.execute() }
        coVerify(exactly = 1) { getRecipesFavoriteUseCase.execute() }
    }

}