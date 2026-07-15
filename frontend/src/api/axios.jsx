import axios from 'axios'

export const api = axios.create({
	baseURL: 'http://localhost:8050/api/v1',
	headers:{
		'Content-Type': 'application/json'
	}
})

api.interceptors.request.use((config) => {
	const token = localStorage.getItem('accessToken')
	if(token){
		config.headers.Authorization = `Bearer ${token}`
	}
	return config
}
    // (error)=>{
    //     return Promise.reject(error)
    // }
)

api.interceptors.response.use(
	(response) => response,
	async (error) => {
		const originalRequest = error.config;

		// Check if the request failed due to an unauthorized error and hasn't been retried yet
		if (error.response && error.response.status === 401 && !originalRequest._retry) {
			originalRequest._retry = true; // Mark the request to avoid infinite loops

			try {
				const refreshToken = localStorage.getItem('refreshToken');
				if (!refreshToken) {
					throw new Error("No refresh token available");
				}

				// Call the refresh route using raw axios (to bypass the request interceptor)
				const response = await axios.post('http://localhost:8050/api/v1/auth/refresh', {}, {
					headers: {
						Authorization: `Bearer ${refreshToken}`,
						'Content-Type': 'application/json'
					}
				});

				const { accessToken, refreshToken: newRefreshToken } = response.data;

				// Save the new tokens
				localStorage.setItem('accessToken', accessToken);
				localStorage.setItem('refreshToken', newRefreshToken);

				// Update authorization header with the new access token
				originalRequest.headers['Authorization'] = `Bearer ${accessToken}`;

				// Retry the original request and return its response
				return api(originalRequest);
			} catch (refreshError) {
					// If refresh token is invalid/expired, log the user out
					localStorage.removeItem('accessToken');
					localStorage.removeItem('refreshToken');
					
					// Optionally redirect to login or reset app state
					window.location.href = '/login'; 
					return Promise.reject(refreshError);
			}
		}

		return Promise.reject(error);
	}
);
