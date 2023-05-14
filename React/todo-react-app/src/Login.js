import React from "react";
import { signin } from "./ApiService";
import { Link } from "react-router-dom";
import { Container, Grid, TextField, Typography, Button } from "@mui/material";
import { GoogleLogin } from "react-google-login";


function Login() {
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        const username = data.get("username");
        const password = data.get("password");
        // api service 의 signin 메서드를 사용해 로그인
        signin({ "username": username, "password": password });
    };
    const handleSocialLogin = (response) => {
        // 서버에 전달
        fetch('/oauth2/google', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: JSON.stringify(response)
        })
            .then(response => response.json())
            .then(user => {
                // 로그인 완료 처리
            });
    }

    return (
        <Container component="main" maxWidth="xs" style={{ margin: "8%" }}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Typography component="h1" variant="h5">
                        로그인
                    </Typography>
                </Grid>
            </Grid>
            <form noValidate onSubmit={handleSubmit}>
                {" "}
                {/* submit 버튼을 누르면 handle submit 이 실행 */}
                <Grid container spacing={2}>
                <Grid item xs={12}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        id="username"
                        label="아이디"
                        name="username"
                        autoComplete="username"
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        name="password"
                        id="password"
                        label="패스워드"
                        type="password"
                        autoComplete="current-password"
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary">
                        로그인
                    </Button>
                    <GoogleLogin
                        clientId='155661665710-ojef8edbrh316c9gjn0ab6pou8mc4en3.apps.googleusercontent.com'
                        buttonText="Log in with Google"
                        onSuccess={handleSocialLogin}
                        onFailure={handleSocialLogin}
                    />

                </Grid>
                <Grid item>
                    <Link to="/signup" variant="body2">
                        계정이 없습니까? 여기서 가입하세요.
                    </Link>
                </Grid>
                </Grid>
            </form>
        </Container>


    );
}

export default Login