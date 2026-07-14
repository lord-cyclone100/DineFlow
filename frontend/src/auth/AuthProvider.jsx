import { useEffect, useEffectEvent, useState } from "react"
import { AuthContext } from "./AuthContext"
import { api } from "../api/axios"

export const AuthProvider = ({children}) => {
  const [currentUser, setCurrentUser] = useState(null)
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  const login = (userDetails) => {
    setCurrentUser(userDetails)
    setIsAuthenticated(true)
  }

  const logout = () => {
    setCurrentUser(null)
    setIsAuthenticated(false)
  }

  // Inside AuthProvider.jsx:
  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem('accessToken');
      if (token) {
        try {
          const response = await api.get('/auth/me');
          login(response.data);
        } catch (err) {
          logout(); // Token might be expired or invalid
        }
      }
    };
    fetchUser();
  }, []);

  return(
    <>
      <AuthContext.Provider
        value={{currentUser, isAuthenticated, login, logout}}
      >
        {children}
      </AuthContext.Provider>
    </>
  )
}