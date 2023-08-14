package com.owlcode.appcomandav3.di

import com.owlcode.appcomandav3.data.passcode.resouce.LoginUserRepositoryImpl
import com.owlcode.appcomandav3.domain.passcode.repository.LoginUserRepository
import com.owlcode.appcomandav3.data.users.resouce.UsuarioRepositoryImpl
import com.owlcode.appcomandav3.domain.users.repository.UsuarioRepository
import com.owlcode.appcomandav3.data.zones.resouce.TableRespositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.UpdateStateTableImpl
import com.owlcode.appcomandav3.data.zones.resouce.ZoneRepositoryImpl
import com.owlcode.appcomandav3.domain.zones.repository.TablesRepository
import com.owlcode.appcomandav3.domain.zones.repository.UpdateStateTableRepository
import com.owlcode.appcomandav3.domain.zones.repository.ZonesRepository
import com.owlcode.appcomandav3.data.orders.resouce.CategoriesRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.DishRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.ListOrdersFulfilledRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.OrderRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.PrintPreCountRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.SendOrdersRepositoryImpl
import com.owlcode.appcomandav3.data.orders.resouce.UpdateColorOrderImpl
import com.owlcode.appcomandav3.domain.orders.repositories.CategoriesRepository
import com.owlcode.appcomandav3.domain.orders.repositories.DishRepository
import com.owlcode.appcomandav3.domain.orders.repositories.ListOrdersFulfilledRepository
import com.owlcode.appcomandav3.domain.orders.repositories.OrderRepository
import com.owlcode.appcomandav3.domain.orders.repositories.PrintPreCountRepository
import com.owlcode.appcomandav3.domain.orders.repositories.SendOrdersRepository
import com.owlcode.appcomandav3.domain.orders.repositories.UpdateColorOrderRespository
import dagger.hilt.android.components.ViewModelComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindUsuarioRepository(impl: UsuarioRepositoryImpl): UsuarioRepository

    @Binds
    abstract fun bindLoginUserRepository(impl: LoginUserRepositoryImpl): LoginUserRepository

    @Binds
    abstract fun bindZoneRepository(impl: ZoneRepositoryImpl): ZonesRepository

    @Binds
    abstract fun bindTableRepository(impl: TableRespositoryImpl): TablesRepository

    @Binds
    abstract fun bindCategoryRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun bindDishRepository(impl: DishRepositoryImpl): DishRepository

    @Binds
    abstract fun bindSendOrderRepository(impl: SendOrdersRepositoryImpl): SendOrdersRepository

    @Binds
    abstract fun bindOrdersFullfilledRepository(impl: ListOrdersFulfilledRepositoryImpl): ListOrdersFulfilledRepository

    @Binds
    abstract fun bindUpdateStateTable(impl: UpdateStateTableImpl): UpdateStateTableRepository

    @Binds
    abstract fun bindOrder(impl: OrderRepositoryImpl): OrderRepository

    @Binds
    abstract fun bindUpdateColorOrder(impl: UpdateColorOrderImpl): UpdateColorOrderRespository

    @Binds
    abstract fun bindPrintPreCountRepository(impl: PrintPreCountRepositoryImpl): PrintPreCountRepository
}