import { useState } from "react"
import { api } from "../../api/axios"

export const LoginForm = () => {

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  })

  const handleChange = (e) => {
    const {name, value} = e.target

    setFormData((prev) =>({...prev, [name]: value}))
  }

  const handleFormSubmit = async (e) => {
    e.preventDefault()
    try{
      const response = await api.post('/auth/login', formData)
      console.log(response.data)
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
    </>
  )
}