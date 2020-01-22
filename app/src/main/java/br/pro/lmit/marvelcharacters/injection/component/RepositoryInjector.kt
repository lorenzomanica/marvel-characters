package br.pro.lmit.marvelcharacters.injection.component

import br.pro.lmit.marvelcharacters.data.DefaultCharacterRepository
import br.pro.lmit.marvelcharacters.injection.module.ApiModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class])
interface RepositoryInjector {

    fun inject(repository: DefaultCharacterRepository)

    @Component.Builder
    interface Builder {

        fun build(): RepositoryInjector

        fun apiModule(module: ApiModule): Builder
    }

}