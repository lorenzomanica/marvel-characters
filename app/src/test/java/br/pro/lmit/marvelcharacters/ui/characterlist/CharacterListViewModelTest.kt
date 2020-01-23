package br.pro.lmit.marvelcharacters.ui.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.pro.lmit.marvelcharacters.LiveDataTestUtil
import br.pro.lmit.marvelcharacters.TestCoroutineRule
import br.pro.lmit.marvelcharacters.data.CharacterRepository
import br.pro.lmit.marvelcharacters.data.Result
import br.pro.lmit.marvelcharacters.data.entity.Character
import br.pro.lmit.marvelcharacters.data.entity.CharacterDataContainer
import br.pro.lmit.marvelcharacters.data.entity.CharacterDataWrapper
import br.pro.lmit.marvelcharacters.util.Event
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CharacterListViewModelTest {


    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CharacterListViewModel

    private lateinit var repository: CharacterRepository

    @Test
    fun load() = coroutineRule.runBlockingTest {

        val itemList: List<Character> = listOf(
            Character(
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
        )

        repository = mock {
            onBlocking {
                this.getCharacters()
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
                                itemList
                            ),
                            "etag"
                        )
                    )
                    )
        }

        viewModel = CharacterListViewModel(repository)

        this.pauseDispatcher()

        viewModel.load()
        Assert.assertEquals(true, LiveDataTestUtil.getValue(viewModel.loading))

        this.resumeDispatcher()

        Assert.assertEquals(false, LiveDataTestUtil.getValue(viewModel.loading))
        Assert.assertEquals(itemList, LiveDataTestUtil.getValue(viewModel.items))
        Assert.assertEquals(itemList.size, LiveDataTestUtil.getValue(viewModel.items).size)
    }

    @Test
    fun viewDetails() = coroutineRule.runBlockingTest {

        repository = mock {
            onBlocking {
                this.getCharacters()
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
                                listOf()
                            ),
                            "etag"
                        )
                    )
                    )
        }
        viewModel = CharacterListViewModel(repository)

        val itemId = 123

        viewModel.viewDetails(itemId)
        Assert.assertEquals(itemId, LiveDataTestUtil.getValue(viewModel.viewDetailsEvent).getContentIfNotHandled())
    }
}