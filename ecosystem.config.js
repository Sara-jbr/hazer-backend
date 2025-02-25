module.exports = {
  apps: [{
    name: 'quarkus-app',
    script: 'java', // For JVM mode
    args: '-jar target/quarkus-app/quarkus-run.jar',
    // For native executable:
    // script: './target/your-executable',
    instances: 'max',
    autorestart: true,
    watch: false,
    max_memory_restart: '1G',
    env: {
      NODE_ENV: 'production'
    }
  }]
};