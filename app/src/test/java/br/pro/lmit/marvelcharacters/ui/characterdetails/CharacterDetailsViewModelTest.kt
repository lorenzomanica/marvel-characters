package br.pro.lmit.marvelcharacters.ui.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.FlakyTest
import br.pro.lmit.marvelcharacters.LiveDataTestUtil
import br.pro.lmit.marvelcharacters.TestCoroutineRule
import br.pro.lmit.marvelcharacters.data.CharacterRepository
import br.pro.lmit.marvelcharacters.data.Result
import br.pro.lmit.marvelcharacters.data.entity.Character
import br.pro.lmit.marvelcharacters.data.entity.CharacterDataContainer
import br.pro.lmit.marvelcharacters.data.entity.CharacterDataWrapper
import br.pro.lmit.marvelcharacters.data.entity.Url
import br.pro.lmit.marvelcharacters.ui.characterlist.CharacterListViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterDetailsViewModelTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CharacterDetailsViewModel

    private lateinit var repository: CharacterRepository


    @Test
    fun load() = coroutineRule.runBlockingTest {

        val mockData = Character(
            123,
            "Name",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        repository = mock {
            onBlocking {
                this.getCharacter(any())
            } doReturn (
                    Result.Success(
                        CharacterDataWrapper(
                            0,
                            "status",
                            null,
                            null,
                            null,
                            CharacterDataContainer(
                                0,
                                1,
                                1,
                                0,
                                listOf(mockData)
                            ),
                            "etag"
                        )
                    )
                    )
        }

        viewModel = CharacterDetailsViewModel(repository)

        this.pauseDispatcher()

        viewModel.load(123)
        Assert.assertEquals(true, LiveDataTestUtil.getValue(viewModel.loading))

        this.resumeDispatcher()

        Assert.assertEquals(false, LiveDataTestUtil.getValue(viewModel.loading))
        Assert.assertEquals(mockData, LiveDataTestUtil.getValue(viewModel.character))
    }

    @Test
    fun openWeb() {

        repository = mock()
        viewModel = CharacterDetailsViewModel(repository)


        val wikiUrl = "http://localhost:1234/wiki"
        val detailsUrl = "http://localhost:1234/details"

        val mockData = Character(
            123,
            "Name",
            null,
            null,
            null,
            listOf(Url("details", detailsUrl), Url("wiki", wikiUrl)),
            null,
            null,
            null,
            null,
            null
        )

        viewModel.openWeb(mockData)
        Assert.assertEquals(detailsUrl, LiveDataTestUtil.getValue(viewModel.openWebEvent).getContentIfNotHandled())
    }

    @FlakyTest
    @Test
    fun openWiki() = coroutineRule.runBlockingTest {

        repository = mock()
        viewModel = CharacterDetailsViewModel(repository)


        val wikiUrl = "http://localhost:1234/wiki"
        val detailsUrl = "http://localhost:1234/details"

        val mockData = Character(
            123,
            "Name",
            null,
            null,
            null,
            listOf(Url("details", detailsUrl), Url("wiki", wikiUrl)),
            null,
            null,
            null,
            null,
            null
        )

        viewModel.openWiki(mockData)
        Assert.assertEquals(wikiUrl, LiveDataTestUtil.getValue(viewModel.openWikiEvent).getContentIfNotHandled())
    }
}