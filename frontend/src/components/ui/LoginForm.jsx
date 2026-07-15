import { useContext, useState } from "react"
import { api } from "../../api/axios"
import { AuthContext } from "../../auth/AuthContext"
import { useNavigate } from "react-router-dom"

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

  return(
    <>
      <form action="" onSubmit={handleFormSubmit}>
        <div>
          <label htmlFor="email">Email : </label>
          <input type="email" name="email" value={formData.email} onChange={handleChange}/>
        </div>
        <div>
          <label htmlFor="password">Password : </label>
          <input type="password" name="password" value={formData.password} onChange={handleChange}/>
        </div>
        <button type="submit">Login</button>
      </form>
      {/* <button onClick={setCurrentUserDetails}>Get Details</button> */}
    </>
  )
}