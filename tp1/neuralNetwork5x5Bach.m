A = importdata('input.txt');


w1 = rand(5,3)
w2 = rand(5,6)
w3 = rand(1,6)

in = [-1*ones(441,1) A.data(:,1) A.data(:,2)]';
V1 = [-1*ones(441,1) zeros(441,5)]';
V2 = [-1*ones(441,1) zeros(441,5)]';
V3 = zeros(1,441);

delta1 = zeros(5,441);
delta2 = zeros(5,441);
delta3 = zeros(1,441);

auxOne = ones(1,441);
auxOne2 = ones(5,441);

S = A.data(:, 3)';

epochs = 10000;
eta = 0.0006;
Err = [1:epochs];

for i = 1:epochs
   V1(2:6, :) = tanh(w1 * in);
   V2(2:6, :) = tanh(w2 * V1);
   V3 = tanh(w3 * V2);
   delta3 = (auxOne - V3.^2).*(S - V3);
   w3 = w3 + eta * delta3 * V2';
   delta2 = (auxOne2 - V2(2:6,:).^2).*(w3(2:6)' * delta3);
   w2 = w2 + eta * delta2 * V1';
   delta1 = (auxOne2 - V1(2:6, :).^2).*(w2(:,2:6)' * delta2);
   w1 = w1 + eta * delta1 * in';
   Err(i) = sum(0.5*(S - V3).^2)/441;
end

%plot(1:epochs, Err);

figure
%scatter3(A.data(:, 1), A.data(:, 2), A.data(:, 3),'RED','filled')
%hold on
scatter3(A.data(:, 1), A.data(:, 2), V3)
