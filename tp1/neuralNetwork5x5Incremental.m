A = importdata('input.txt');


w1 = rand(5,3);
w2 = rand(5,6);
w3 = rand(1,6);

cantEntries = 300;

%variables de forward y backpropagation
in = [-1*ones(cantEntries,1) A.data(1:cantEntries,1) A.data(1:cantEntries,2)]';
V1 = [-1*ones(cantEntries,1) zeros(cantEntries,5)]';
V2 = [-1*ones(cantEntries,1) zeros(cantEntries,5)]';
V3 = zeros(1,cantEntries);

%variables de testeo
total = 441;%size(A.data(:,1));
inT = [-1*ones(total-cantEntries,1) A.data((cantEntries+1):total,1) A.data((cantEntries+1):total,2)]';
V1T = [-1*ones(total-cantEntries,1) zeros(total-cantEntries,5)]';
V2T = [-1*ones(total-cantEntries,1) zeros(total-cantEntries,5)]';
V3T = zeros(1,total-cantEntries);

%delta1 = zeros(5,cantEntries);
%delta2 = zeros(5,cnatEntries);
%delta3 = zeros(1,cantEntries);
delta1 = zeros(5,1);
delta2 = zeros(5,1);
delta3 = 0;

%auxOne = ones(1,cantEntries);
%auxOne2 = ones(5,cantEntries);
auxOne = 1;
auxOne2 = ones(5,1);


S = A.data(:, 3)';

epochs = 3000;
eta = 0.2;

Err = zeros(epochs*cantEntries,1);

ErrT = zeros(epochs*cantEntries,1);

k = 0;

for i = 1:epochs
    for j = 1:cantEntries
        
        %forward and back propagation
        V1(2:6, j) = tanh(w1 * in(:,j));
        V2(2:6, j) = tanh(w2 * V1(:,j));
        V3(j) = tanh(w3 * V2(:,j));
        delta3 = (auxOne - V3(j).^2).*(S(j) - V3(j));
        w3 = w3 + eta * delta3 * V2(:,j)';
        delta2 = (auxOne2 - V2(2:6,j).^2).*(w3(2:6)' * delta3);
        w2 = w2 + eta * delta2 * V1(:,j)';
        delta1 = (auxOne2 - V1(2:6, j).^2).*(w2(:,2:6)' * delta2);
        w1 = w1 + eta * delta1 * in(:,j)';
        
        %learning error
        Err((i-1)*cantEntries + j) = 0.5*(S(j) - V3(j))^2;
        
        %aux = mod(k,cantEntries-total) + 1;
        %testing value
        V1T(2:6,:) = tanh(w1 * inT);
        V2T(2:6,:) = tanh(w2 * V1T);
        V3T = tanh(w3 * V2T);
        
        %testing error
        ErrT((i-1)*cantEntries + j) = sum(0.5*(S((cantEntries+1):total) - V3T).^2)/(total-cantEntries);
    end
    in(:,randperm(cantEntries));
end

%plot(1:epochs*cantEntries, Err)
%hold on
plot(1:epochs*cantEntries, Err, 1:epochs*cantEntries, ErrT);
Err(end)
ErrT(end)

%figure
%scatter3(A.data(:, 1), A.data(:, 2), A.data(:, 3),'RED','filled')
%hold on
%scatter3(A.data(:, 1), A.data(:, 2), V3)
