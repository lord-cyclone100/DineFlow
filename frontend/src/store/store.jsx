import { create } from 'zustand'

export const useChangePasswordToggle = create((set) => ({
    changePasswordModalShow: false,
    setChangePasswordModalShow: (val) => set({ changePasswordModalShow: val }),
}))