import { useContext } from "react"
import { AuthContext } from "./AuthContext"
import { Loader } from "../components/layout/Loader"
import { Navigate, Outlet } from "react-router-dom"
import { Header } from "../components/layout/Header"
import { Footer } from "../components/layout/Footer"

export const ProtectedRoute = () => {
  const { isAuthenticated, loading } = useContext(AuthContext)
  if(loading){
    return(
      <>
        <Loader />
      </>
    )
  }
  return(
    <>
      {
        isAuthenticated ? <><Header /> <Outlet /> <Footer /></> : <Navigate to='/login' replace />
      }
    </>
  )
}