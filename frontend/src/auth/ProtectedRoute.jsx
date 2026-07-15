import { useContext } from "react"
import { AuthContext } from "./AuthContext"
import { Loader } from "../components/layout/Loader"
import { Navigate, Outlet } from "react-router-dom"

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
        isAuthenticated ? <Outlet /> : <Navigate to='/login' replace />
      }
    </>
  )
}