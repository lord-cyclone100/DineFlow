import { useContext, useState } from "react"
import { api } from "../../api/axios"
import { AuthContext } from "../../auth/AuthContext"
import { useNavigate } from "react-router-dom"
import { UpdateRolesModal } from "./UpdateRolesModal"

export const LoginForm = () => {

  const { login } = useContext(AuthContext)
  const navigate = useNavigate()

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  })

  const handleChange = (e) => {
    const {name, value} = e.target

    setFormData((prev) =>({...prev, [name]: value}))
  }

  const setCurrentUserDetails = async () => {
  try {
    const response = await api.get('/auth/me')
    console.log(response.data);
    login(response.data)
    navigate('/')
  } catch (error) {
    console.error("Failed to fetch user details:", error);
  }
}


  const handleFormSubmit = async (e) => {
    e.preventDefault()
    try{
      const response = await api.post('/auth/login', formData)
      localStorage.setItem('accessToken',response.data.accessToken)
      localStorage.setItem('refreshToken',response.data.refreshToken)
      console.log(response.data)
      await setCurrentUserDetails()
    }
    catch(error){
      console.log(error.response.data.message)
    }
  }

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8050/oauth2/authorization/google"
  }

  return(
    <>
      <form action="" onSubmit={handleFormSubmit}>
        <div>
          <label htmlFor="email">Email : </label>
          <input type="email" name="email" value={formData.email} onChange={handleChange} className="border"/>
        </div>
        <div>
          <label htmlFor="password">Password : </label>
          <input type="password" name="password" value={formData.password} onChange={handleChange} className="border"/>
        </div>
        <button type="submit" className="border p-1.5 bg-sky-700 text-white">Login</button>
        <p>or</p>
      </form>
      <button className="border p-1.5 bg-sky-700 text-white" onClick={handleGoogleLogin}>Login with Google</button>
      <UpdateRolesModal />
    </>
  )
}