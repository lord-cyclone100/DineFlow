import { create } from 'zustand'

export const useChangePasswordToggle = create((set) => ({
    changePasswordModalShow: false,
    setChangePasswordModalShow: (val) => set({ changePasswordModalShow: val }),
}))

export const useChangeUserRolesToggle = create((set) => ({
    changeUserRolesModalShow: false,
    setChangeUserRolesModalShow: (val) => set({ changeUserRolesModalShow: val }),
}))

export const useCreateNewRestaurantModal = create((set) => ({
    createNewRestaurantModalShow: false,
    setCreateNewRestaurantModalShow: (val) => set({ createNewRestaurantModalShow: val })
}))

export const useUpdateRestaurantModal = create((set) => ({
    updateRestaurantModalShow: false,
    setUpdateRestaurantModalShow: (val) => set({ updateRestaurantModalShow: val })
}))