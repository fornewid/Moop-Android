package soup.movie.di.ui

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import soup.movie.data.TheaterEditManager
import soup.movie.di.scope.FragmentScope
import soup.movie.settings.impl.TheatersSetting
import soup.movie.ui.theater.edit.cgv.CgvEditFragment
import soup.movie.ui.theater.edit.cgv.CgvEditPresenter
import soup.movie.ui.theater.edit.lotte.LotteEditFragment
import soup.movie.ui.theater.edit.lotte.LotteEditPresenter
import soup.movie.ui.theater.edit.megabox.MegaboxEditFragment
import soup.movie.ui.theater.edit.megabox.MegaboxEditPresenter
import soup.movie.ui.theater.edit.tab.TheaterEditChildContract

@Module
abstract class TheaterEditTabUiModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [
        CgvModule::class
    ])
    internal abstract fun provideCgvFragment(): CgvEditFragment

    @Module
    class CgvModule {

        @FragmentScope
        @Provides
        fun presenter(manager: TheaterEditManager,
                      theatersSetting: TheatersSetting):
                TheaterEditChildContract.Presenter =
                CgvEditPresenter(manager, theatersSetting)
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [
        LotteModule::class
    ])
    internal abstract fun provideLotteFragment(): LotteEditFragment

    @Module
    class LotteModule {

        @FragmentScope
        @Provides
        fun presenter(manager: TheaterEditManager,
                      theatersSetting: TheatersSetting):
                TheaterEditChildContract.Presenter =
                LotteEditPresenter(manager, theatersSetting)
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [
        MegaboxModule::class
    ])
    internal abstract fun provideMegaboxFragment(): MegaboxEditFragment

    @Module
    class MegaboxModule {

        @FragmentScope
        @Provides
        fun presenter(manager: TheaterEditManager,
                      theatersSetting: TheatersSetting):
                TheaterEditChildContract.Presenter =
                MegaboxEditPresenter(manager, theatersSetting)
    }
}